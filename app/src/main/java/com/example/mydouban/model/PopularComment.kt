package com.example.mydouban.model

import java.text.SimpleDateFormat

data class PopularComment(
    val author: MovieDetailDto.Author,
    val content: String,
    val createdAt: String,
    val id: String,
    val rating: MovieDetailDto.ReviewRating,
    val subjectId: String,
    val usefulCount: Int
) {
    val createdDate: String get() {
        val datetimeFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = datetimeFormatter.parse(createdAt)
        val dateFormatter = SimpleDateFormat("yyyy年MM月dd日")
        return dateFormatter.format(date)
    }
}