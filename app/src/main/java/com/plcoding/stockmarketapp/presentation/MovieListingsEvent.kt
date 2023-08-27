package com.plcoding.stockmarketapp.presentation.company_listings

sealed class MovieListingsEvent {
    object Refresh: MovieListingsEvent()
    data class OnSearchQueryChange(val query: String): MovieListingsEvent()
}
