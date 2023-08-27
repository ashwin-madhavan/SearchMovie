package com.plcoding.stockmarketapp.domain.repository

import com.plcoding.stockmarketapp.domain.model.MovieInfo
import com.plcoding.stockmarketapp.domain.model.MovieListing
import com.plcoding.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieListings(
        fetchFromRemote: Boolean,
        query: String
    ) : Flow<Resource<List<MovieListing>>>

    suspend fun getMovieInfo(
        id: Long
    ): Resource<MovieInfo>
}