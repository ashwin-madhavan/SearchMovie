package com.plcoding.stockmarketapp.data.mapper

import com.plcoding.stockmarketapp.data.local.MovieListingEntity
import com.plcoding.stockmarketapp.data.local.MovieReviewEntity
import com.plcoding.stockmarketapp.data.remote.dto.MovieInfoDto
import com.plcoding.stockmarketapp.domain.model.MovieInfo
import com.plcoding.stockmarketapp.domain.model.MovieListing
import com.plcoding.stockmarketapp.domain.model.MovieReview

fun MovieReviewEntity.toMovieReview(): MovieReview {
    return MovieReview(
        movie_id = movie_id,
        overview = overview,
        release_date = release_date,
        title = title,
        review = review,
        review_date = review_date,
        rating = rating
    )
}

fun MovieReview.toMovieReviewEntity(): MovieReviewEntity {
    return MovieReviewEntity(
        movie_id = movie_id,
        overview = overview,
        release_date = release_date,
        title = title,
        review = review,
        review_date = review_date,
        rating = rating
    )
}