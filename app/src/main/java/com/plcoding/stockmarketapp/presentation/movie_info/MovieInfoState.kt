package com.plcoding.stockmarketapp.presentation.company_info

import com.plcoding.stockmarketapp.domain.model.MovieInfo

data class MovieInfoState(
    val movie: MovieInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
