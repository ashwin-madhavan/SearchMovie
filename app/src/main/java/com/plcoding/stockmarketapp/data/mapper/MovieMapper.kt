package com.plcoding.stockmarketapp.data.mapper

import com.example.searchmovietitle.model.Movie
import com.plcoding.stockmarketapp.data.local.MovieListingEntity
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

fun Movie.toMovieListingEntity(): MovieListingEntity {
    return MovieListingEntity(
        movie_id = id,
        overview = overview,
        release_date = release_date,
        title = title,
    )
}

/**
fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}
**/