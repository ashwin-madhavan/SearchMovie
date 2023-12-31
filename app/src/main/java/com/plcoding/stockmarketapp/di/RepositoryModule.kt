package com.plcoding.stockmarketapp.di

import com.plcoding.stockmarketapp.data.repository.MovieRepositoryImpl
import com.plcoding.stockmarketapp.data.repository.MovieReviewRepositoryImpl
import com.plcoding.stockmarketapp.domain.repository.MovieRepository
import com.plcoding.stockmarketapp.domain.repository.MovieReviewRepository
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
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindMovieReviewRepository(
        movieReviewRepositoryImpl: MovieReviewRepositoryImpl
    ): MovieReviewRepository
}