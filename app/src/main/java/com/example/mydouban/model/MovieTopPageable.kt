package com.example.mydouban.model

data class MovieTopPageable(
    val count: Int,
    val start: Int,
    val subjects: List<MovieSubject>,
    val title: String,
    val total: Int
) {
    data class MovieSubject(
        val alt: String,
        val casts: List<Cast>,
        val collectCount: Int,
        val directors: List<Director>,
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


    data class Rating(
        val average: Double,
        val details: Details,
        val max: Int,
        val min: Int,
        val stars: String
    )


    data class Details(
        val `1`: Int,
        val `2`: Int,
        val `3`: Int,
        val `4`: Int,
        val `5`: Int
    )


    data class Director(
        val alt: String,
        val avatars: AvatarsX,
        val id: String,
        val name: String,
        val nameEn: String
    )


    data class Images(
        val large: String,
        val medium: String,
        val small: String
    )

    data class Cast(
        val alt: String,
        val avatars: Avatars,
        val id: String,
        val name: String,
        val nameEn: String
    )

    data class Avatars(
        val large: String,
        val medium: String,
        val small: String
    )


    data class AvatarsX(
        val large: String,
        val medium: String,
        val small: String
    )
}
