package com.example.searchmovietitle.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>
)

data class Movie(
    val id: Long,
    val overview: String,
    val release_date: String,
    val title: String,
)