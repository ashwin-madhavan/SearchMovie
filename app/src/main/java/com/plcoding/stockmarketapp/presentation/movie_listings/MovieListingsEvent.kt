package com.plcoding.stockmarketapp.presentation.movie_listings

sealed class MovieListingsEvent {
    object Refresh: MovieListingsEvent()
    data class OnSearchQueryChange(val query: String): MovieListingsEvent()
}
