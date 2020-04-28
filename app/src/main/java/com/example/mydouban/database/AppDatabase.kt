package com.example.mydouban.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydouban.database.dao.CollectDao
import com.example.mydouban.database.entity.Collect

@Database(entities = arrayOf(Collect::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun collectDao(): CollectDao
}