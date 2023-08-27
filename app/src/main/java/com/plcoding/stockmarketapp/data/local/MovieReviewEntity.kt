package com.plcoding.stockmarketapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieReviewEntity(
    val movie_id: Long,
    val overview: String,
    val release_date: String,
    val title: String,
    val review: String,
    val review_date: String,
    val rating: Int,
    @PrimaryKey val id: Int? = null
)
