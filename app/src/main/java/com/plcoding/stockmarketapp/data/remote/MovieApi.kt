package com.plcoding.stockmarketapp.data.remote

import com.example.searchmovietitle.model.MovieResponse
import com.plcoding.stockmarketapp.data.remote.dto.MovieInfoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("search/movie")
    suspend fun getListings(@Query("query") query: String): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieInfo(@Path("movie_id") movieId: Long): MovieInfoDto
}