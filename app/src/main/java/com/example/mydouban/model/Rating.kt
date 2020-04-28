package com.example.mydouban.model

data class Rating(
    val average: Float,
    val details: Details,
    val max: Int,
    val min: Int,
    val stars: String
)