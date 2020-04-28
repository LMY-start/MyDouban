package com.example.mydouban.model

data class MovieTopPageable(
    val count: Int,
    val start: Int,
    val subjects: List<MovieSubject>,
    val title: String,
    val total: Int
)

data class Director(
    val alt: String,
    val avatars: AvatarsX,
    val id: String,
    val name: String,
    val nameEn: String
)

data class AvatarsX(
    val large: String,
    val medium: String,
    val small: String
)

