package com.example.mydouban.model

data class MovieSubject(
        val alt: String,
        val casts: List<Cast>,
        val collectCount: Int,
        val directors: List<Cast>,
        val durations: List<String>,
        val genres: List<String>,
        val hasVideo: Boolean,
        val id: String,
        val images: Images,
        val mainlandPubdate: String,
        val originalTitle: String,
        val pubdates: List<String>,
        val rating: Rating,
        val subtype: String,
        val title: String,
        val year: String
    )