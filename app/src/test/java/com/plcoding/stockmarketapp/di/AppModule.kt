package com.plcoding.stockmarketapp.di

import com.plcoding.stockmarketapp.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val accessToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MDI4NTQ2ZDIwMjA3OWM5YzJkZjU2NmIwOWVlODZmOCIsInN1YiI6IjY0YmIzNjg4NThlZmQzMDExYzNlOWM1NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.on3kpSkegdhzfdl5jpJXGIkQekoI_rPPzcSJOUM7cTE"

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest: Request = chain.request()
                val newRequest: Request = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }


}