package com.plcoding.stockmarketapp.domain.model

data class MovieReview(
    val movie_id: Long,
    val overview: String,
    val release_date: String,
    val title: String,

    val review: String,
    val review_date: String,
    val rating: Int,
)
