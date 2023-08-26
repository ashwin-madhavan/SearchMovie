package com.plcoding.stockmarketapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListingEntities: List<MovieListingEntity>
    )

    @Query("DELETE FROM movielistingentity")
    suspend fun clearCompanyListings()

    @Query(
        """
            SELECT * 
            FROM movielistingentity
            WHERE LOWER(title) LIKE '%' || LOWER(:query) || '%';
        """
    )
    suspend fun searchCompanyListing(query: String): List<MovieListingEntity>
}