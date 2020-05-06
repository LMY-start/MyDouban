package com.example.mydouban.model

class MovieDetail(detailDto: MovieDetailDto) {
    val title: String = detailDto.title

    val originalTitle: String = "${detailDto.originalTitle} (${detailDto.year})"

    val basicInfo =
        "${detailDto.countries[0]} / ${detailDto.genres.joinToString(" ")} / 上映时间：${detailDto.pubdates[0]} / 片长: ${detailDto.durations[0]}"

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
}