package com.plcoding.stockmarketapp.data.repository

import android.util.Log
import com.plcoding.stockmarketapp.data.csv.CSVParser
import com.plcoding.stockmarketapp.data.local.MovieDatabase
import com.plcoding.stockmarketapp.data.mapper.toMovieListing
import com.plcoding.stockmarketapp.data.mapper.toMovieListingEntity
import com.plcoding.stockmarketapp.data.remote.MovieApi
import com.plcoding.stockmarketapp.domain.model.MovieListing
import com.plcoding.stockmarketapp.domain.repository.MovieRepository
import com.plcoding.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val db: MovieDatabase,
    private val movieListingsParser: CSVParser<MovieListing>,
) : MovieRepository {

    private val dao = db.dao

    override suspend fun getMovieListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<MovieListing>>> {
        Log.d("TAG - RepositoryImpl", fetchFromRemote.toString())
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchMovieListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toMovieListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            Log.d("TAG - RepositoryImpl1", shouldJustLoadFromCache.toString())
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings(query)
                Log.d("TAG - RepositoryImpl2", response.toString())
                response.results
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                Log.d("TAG - RepositoryImpl2", "Couldn't load data")
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                Log.d("TAG - RepositoryImpl2", "Couldn't load data")
                null
            }

            Log.d("TAG - RepositoryImpl3", remoteListings.toString())


            remoteListings?.let { listings ->
                dao.clearMovieListings()
                dao.insertMovieListings(
                    listings.map { it.toMovieListingEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchMovieListing("")
                        .map { it.toMovieListing() }
                ))
                emit(Resource.Loading(false))
            }

        }
    }

}