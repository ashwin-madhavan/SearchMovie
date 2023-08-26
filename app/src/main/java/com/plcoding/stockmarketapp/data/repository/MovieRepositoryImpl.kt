package com.plcoding.stockmarketapp.data.repository

import com.plcoding.stockmarketapp.data.local.MovieDatabase
import com.plcoding.stockmarketapp.data.mapper.toMovieListing
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
import com.plcoding.stockmarketapp.data.csv.CSVParser
import com.plcoding.stockmarketapp.data.mapper.toMovieListingEntity

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
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toMovieListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings(query)
                movieListingsParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toMovieListingEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toMovieListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

}