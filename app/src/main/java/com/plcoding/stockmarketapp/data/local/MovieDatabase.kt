package com.plcoding.stockmarketapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieListingEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val dao: MovieDao
}