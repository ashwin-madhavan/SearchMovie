package com.plcoding.stockmarketapp.domain.repository

import com.plcoding.stockmarketapp.domain.model.MovieReview
import com.plcoding.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieReviewRepository {

    suspend fun addMovieReview(movieReview: MovieReview)

    suspend fun getMovieReviewListings(): Flow<Resource<List<MovieReview>>>
}