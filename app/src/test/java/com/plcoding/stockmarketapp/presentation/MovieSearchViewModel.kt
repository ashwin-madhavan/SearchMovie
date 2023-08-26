package com.plcoding.stockmarketapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.stockmarketapp.data.remote.MovieApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val api: MovieApi
) : ViewModel() {

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                val movieResponse = api.getMovies(query)
                Log.d("TAG", movieResponse.toString())
            } catch (e: Exception) {
                // Handle error cases here
            }
        }
    }
}