package com.example.mydouban.model

data class MovieDetail(val detailDto: MovieDetailDto) {
    val title: String = detailDto.title
    val originalTitle: String = "${detailDto.originalTitle} (${detailDto.year})"
    val basicInfo = "${detailDto.countries[0]} / ${detailDto.genres.joinToString(" ")} / 上映时间：${detailDto.pubdates[0]} / 片长: ${detailDto.durations[0]}"
    val poster = detailDto.images.medium
}