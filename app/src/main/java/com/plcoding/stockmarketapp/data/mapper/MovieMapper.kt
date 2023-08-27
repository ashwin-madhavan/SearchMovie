package com.plcoding.stockmarketapp.data.mapper

import com.plcoding.stockmarketapp.data.local.MovieListingEntity
import com.plcoding.stockmarketapp.data.remote.dto.MovieInfoDto
import com.plcoding.stockmarketapp.domain.model.MovieInfo
import com.plcoding.stockmarketapp.domain.model.MovieListing

fun MovieListingEntity.toMovieListing(): MovieListing {
    return MovieListing(
        movie_id = movie_id,
        overview = overview,
        release_date = release_date,
        title = title,
    )
}

fun MovieInfo.toMovieListingEntity(): MovieListingEntity {
    return MovieListingEntity(
        movie_id = id,
        overview = overview,
        release_date = release_date,
        title = title,
    )
}

fun MovieInfoDto.toMovieInfo(): MovieInfo {
    return MovieInfo(
        id = id ?: 0,
        overview = overview ?: "",
        release_date = release_date ?: "",
        title = title ?: "",
    )
}