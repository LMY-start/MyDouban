package com.example.mydouban.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Collect(

    @PrimaryKey
    val uid: Int,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "year")
    val year: Int?,

    @ColumnInfo(name = "average")
    val average: Double?,

    @ColumnInfo(name = "country")
    val country: String?,

    @ColumnInfo(name = "genres")
    val genres: String?,

    @ColumnInfo(name = "directors")
    val directors: String?,

    @ColumnInfo(name = "casts")
    val casts: String?,

    @ColumnInfo(name = "createTime")
    val createTime: String?

)