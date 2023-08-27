package com.plcoding.stockmarketapp.data.remote.dto

import com.plcoding.stockmarketapp.domain.model.MovieInfo

data class MovieResponseDto(
    val page: Int,
    val results: List<MovieInfo>
)