package com.example.mydouban.model

class MovieDetail(detailDto: MovieDetailDto) {
    val id: String = detailDto.id

    val title: String = detailDto.title

    val originalTitle: String = "${detailDto.originalTitle} (${detailDto.year})"

    val country: String = detailDto.countries[0]

    val basicInfo =
        "$country / ${detailDto.genres.joinToString(" ")} / 上映时间：${detailDto.pubdates[0]} / 片长: ${detailDto.durations[0]}"

    val poster = detailDto.images.medium

    val summary: String = detailDto.summary

    val ratingDetail = RatingDetail(
        detailDto.rating,
        detailDto.wishCount,
        detailDto.collectCount,
        detailDto.ratingsCount
    )

    val tags: List<String> = detailDto.tags

    val casts: MutableList<Cast> = mutableListOf()

    val comments: List<PopularComment> = detailDto.popularComments

    val videos: List<MovieDetailDto.Video> = detailDto.videos

    val year: Int = detailDto.year.toInt()

    val genres: List<String> = detailDto.genres

    var isCollected: Boolean = false
}