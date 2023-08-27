package com.plcoding.stockmarketapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieReview(
        movieReviewEntity: MovieReviewEntity
    )

    @Query("SELECT * FROM moviereviewentity")
    suspend fun getAllMovieReviews(): List<MovieReviewEntity>
}