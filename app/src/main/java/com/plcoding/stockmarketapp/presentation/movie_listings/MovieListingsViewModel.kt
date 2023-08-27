package com.plcoding.stockmarketapp.presentation.movie_listings

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.stockmarketapp.domain.repository.MovieRepository
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

    fun onEvent(event: MovieListingsEvent) {
        when(event) {
            is MovieListingsEvent.Refresh -> {
                getMovieListings(fetchFromRemote = true) // search from api
            }
            is MovieListingsEvent.OnSearchQueryChange -> {
                Log.d("TAG - viewModel", event.query)
                state = state.copy(searchQuery = event.query) // change query
                searchJob?.cancel() // cancel current running jbo
                searchJob = viewModelScope.launch {
                    delay(500L) // allow buffer for inputs
                    getMovieListings(fetchFromRemote = true)
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
}