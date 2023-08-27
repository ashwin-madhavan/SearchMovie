package com.plcoding.stockmarketapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [MovieReviewEntity::class],
    version = 1
)
abstract class MovieReviewDatabase : RoomDatabase() {
    abstract val dao: MovieReviewDao
}