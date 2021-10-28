package com.example.bookreviewex.repository.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookreviewex.repository.localdb.dao.ReviewDAO
import com.example.bookreviewex.repository.localdb.entity.ReviewEntity

@Database(
    entities = [ReviewEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ReviewBookAppDatabase:RoomDatabase() {
    companion object{
        private const val DB_NAME = "ReviewBookApp.db"
    }

    abstract fun reviewDAO(): ReviewDAO
}