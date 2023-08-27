package com.plcoding.stockmarketapp.data.remote

import com.example.searchmovietitle.model.MovieResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("search/movie")
    suspend fun getListings(@Query("query") query: String): MovieResponse

}