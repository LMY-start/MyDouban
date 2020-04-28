package com.example.mydouban.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mydouban.database.entity.Collect

interface CollectDao {

    @Query("SELECT * FROM collect Order by uid desc")
    fun getAll(): List<Collect>

    @Query("SELECT * FROM collect WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Collect>

    @Query("SELECT * FROM collect WHERE title LIKE :title ")
    fun findByTitle(title: String): List<Collect>

    @Insert
    fun insertAll(vararg collects: Collect)

    @Delete
    fun delete(collect: Collect)
}