package com.plcoding.stockmarketapp.presentation.company_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.stockmarketapp.domain.repository.MovieRepository
import com.plcoding.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository
) : ViewModel() {

    var state by mutableStateOf(MovieInfoState())

    init {
        viewModelScope.launch {
            val movie_id = savedStateHandle.get<Long>("movie_id") ?: return@launch
            state = state.copy(isLoading = true)
            val movieInfoResult = async { repository.getMovieInfo(movie_id) }
            when (val result = movieInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        movie = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        movie = null
                    )
                }

                else -> Unit
            }
        }
    }
}