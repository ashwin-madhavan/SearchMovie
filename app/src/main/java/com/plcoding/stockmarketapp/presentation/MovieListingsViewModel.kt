package com.plcoding.stockmarketapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.stockmarketapp.domain.repository.MovieRepository
import com.plcoding.stockmarketapp.presentation.company_listings.CompanyListingsEvent
import com.plcoding.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListingsViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    var state by mutableStateOf(MovieListingsState())
    private var searchJob: Job? = null

    init {
        getMovieListings()
    }

    fun onEvent(event: CompanyListingsEvent) {
        when(event) {
            is CompanyListingsEvent.Refresh -> {
                getMovieListings(fetchFromRemote = true) // search from api
            }
            is CompanyListingsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query) // change query
                searchJob?.cancel() // cancel current running jbo
                searchJob = viewModelScope.launch {
                    delay(500L) // allow buffer for inputs
                    getMovieListings()
                }
            }
        }
    }

    private fun getMovieListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getMovieListings(fetchFromRemote, query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(movies = listings)
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }

                }
        }
    }

    /**
    fun searchMovies(query: String) {
    viewModelScope.launch {
    try {
    val movieResponse = api.getListings(query)
    Log.d("TAG", movieResponse.toString())
    } catch (e: Exception) {
    // Handle error cases here
    }
    }
    }
     **/
}