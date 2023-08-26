package com.plcoding.stockmarketapp.di

import com.plcoding.stockmarketapp.data.csv.CSVParser
import com.plcoding.stockmarketapp.data.csv.MovieListingsParser
import com.plcoding.stockmarketapp.data.repository.MovieRepositoryImpl
import com.plcoding.stockmarketapp.domain.model.MovieListing
import com.plcoding.stockmarketapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListingsParser(
        movieListingsParser: MovieListingsParser
    ): CSVParser<MovieListing>


    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}