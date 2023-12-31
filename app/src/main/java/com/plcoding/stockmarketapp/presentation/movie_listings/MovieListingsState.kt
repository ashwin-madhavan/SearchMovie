package com.plcoding.stockmarketapp.presentation.movie_listings

import com.plcoding.stockmarketapp.domain.model.MovieListing

data class MovieListingsState(
    val movies: List<MovieListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
