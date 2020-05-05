package com.example.mydouban.model

data class Collect(

    val id: Int,
    val title: String?,
    val image: String?,
    val year: Int?,
    val average: Float?,
    val country: String?,
    val genres: String?,
    val directors: String?,
    val casts: String?,
    val createTime: String?,
    val titleInfo: String? = "$title($year)",
    val description: String? = "$year / $country / $genres / $directors / $casts"

)