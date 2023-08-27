package com.plcoding.stockmarketapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieListings(
        movieListingEntities: List<MovieListingEntity>
    )

    @Query("DELETE FROM movielistingentity")
    suspend fun clearMovieListings()

    @Query(
        """
            SELECT * 
            FROM movielistingentity
            WHERE LOWER(title) LIKE '%' || LOWER(:query) || '%';
        """
    )
    suspend fun searchMovieListing(query: String): List<MovieListingEntity>
}