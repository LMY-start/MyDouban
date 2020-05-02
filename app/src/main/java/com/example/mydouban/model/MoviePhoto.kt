package com.example.mydouban.model

data class MoviePhoto(
    val count: Int,
    val photos: List<Photo>,
    val start: Int,
    val subject: MovieSubject,
    val total: Int
)


data class Photo(
    val thumb: String
)

