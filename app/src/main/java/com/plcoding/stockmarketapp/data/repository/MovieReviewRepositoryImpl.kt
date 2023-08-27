package com.plcoding.stockmarketapp.data.repository

import com.plcoding.stockmarketapp.data.local.MovieReviewDatabase
import com.plcoding.stockmarketapp.data.mapper.toMovieReview
import com.plcoding.stockmarketapp.data.mapper.toMovieReviewEntity
import com.plcoding.stockmarketapp.domain.model.MovieReview
import com.plcoding.stockmarketapp.domain.repository.MovieReviewRepository
import com.plcoding.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieReviewRepositoryImpl @Inject constructor(
    private val db: MovieReviewDatabase,
) : MovieReviewRepository {

    private val dao = db.dao

    override suspend fun addMovieReview(movieReview: MovieReview) {
        dao.insertMovieReview(movieReview.toMovieReviewEntity())
    }

    override suspend fun getMovieReviewListings(): Flow<Resource<List<MovieReview>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.getAllMovieReviews()
            emit(Resource.Success(
                data = localListings.map { it.toMovieReview() }
            ))
            emit(Resource.Loading(false))
        }
    }

}