package com.example.searchmovietitle.model

import com.plcoding.stockmarketapp.domain.model.MovieInfo

data class MovieResponse(
    val page: Int,
    val results: List<MovieInfo>
)
