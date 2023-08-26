package com.plcoding.stockmarketapp.data.repository

import com.plcoding.stockmarketapp.data.remote.MovieApi
import com.plcoding.stockmarketapp.domain.model.MovieInfo
import com.plcoding.stockmarketapp.domain.repository.MovieRepository
import com.plcoding.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
){

}