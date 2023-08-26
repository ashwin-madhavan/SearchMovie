package com.plcoding.stockmarketapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieListingEntity(
    val movie_id: Long, // originally was "id"
    val overview: String,
    val release_date: String,
    val title: String,
    @PrimaryKey val id: Int? = null
)
