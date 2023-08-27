package com.plcoding.stockmarketapp.presentation.company_info

import com.plcoding.stockmarketapp.domain.model.MovieInfo
import com.plcoding.stockmarketapp.domain.model.MovieListing
import com.plcoding.stockmarketapp.domain.model.MovieReview

data class MovieInfoState(
    val movie: MovieInfo? = null,
    val movieReviews: List<MovieReview> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
