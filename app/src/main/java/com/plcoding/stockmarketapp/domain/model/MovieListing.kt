package com.plcoding.stockmarketapp.domain.model

data class MovieListing(
    val movie_id: Long, // originally was 'id'
    val overview: String,
    val release_date: String,
    val title: String
)