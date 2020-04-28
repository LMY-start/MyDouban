package com.example.mydouban.model

data class MovieTopPageable(
    val count: Int,
    val start: Int,
    val subjects: List<MovieSubject>,
    val title: String,
    val total: Int
)

