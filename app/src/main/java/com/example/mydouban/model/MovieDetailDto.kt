package com.example.mydouban.model

data class MovieDetailDto(
    val aka: List<String>,
    val alt: String,
    val blooperUrls: List<String>,
    val bloopers: List<Blooper>,
    val casts: List<Cast>,
    val clipUrls: List<String>,
    val clips: List<Clip>,
    val collectCount: Int,
    val collection: Any,
    val commentsCount: Int,
    val countries: List<String>,
    val currentSeason: Any,
    val directors: List<Cast>,
    val doCount: Any,
    val doubanSite: String,
    val durations: List<String>,
    val episodesCount: Any,
    val genres: List<String>,
    val hasSchedule: Boolean,
    val hasTicket: Boolean,
    val hasVideo: Boolean,
    val id: String,
    val images: Images,
    val languages: List<String>,
    val mainlandPubdate: String,
    val mobileUrl: String,
    val originalTitle: String,
    val photos: List<Photo>,
    val photosCount: Int,
    val popularComments: List<PopularComment>,
    val popularReviews: List<PopularReview>,
    val pubdate: String,
    val pubdates: List<String>,
    val rating: Rating,
    val ratingsCount: Int,
    val reviewsCount: Int,
    val scheduleUrl: String,
    val seasonsCount: Any,
    val shareUrl: String,
    val subtype: String,
    val summary: String,
    val tags: List<String>,
    val title: String,
    val trailerUrls: List<String>,
    val trailers: List<Trailer>,
    val videos: List<Video>,
    val website: String,
    val wishCount: Int,
    val writers: List<Cast>,
    val year: String
)

data class Blooper(
    val alt: String,
    val id: String,
    val medium: String,
    val resourceUrl: String,
    val small: String,
    val subjectId: String,
    val title: String
)


data class Clip(
    val alt: String,
    val id: String,
    val medium: String,
    val resourceUrl: String,
    val small: String,
    val subjectId: String,
    val title: String
)


data class Photo(
    val alt: String,
    val cover: String,
    val icon: String,
    val id: String,
    val image: String,
    val thumb: String
)

data class PopularComment(
    val author: Author,
    val content: String,
    val createdAt: String,
    val id: String,
    val reviewRating: ReviewRating,
    val subjectId: String,
    val usefulCount: Int
)

data class PopularReview(
    val alt: String,
    val author: Author,
    val id: String,
    val reviewRating: ReviewRating,
    val subjectId: String,
    val summary: String,
    val title: String
)

data class Trailer(
    val alt: String,
    val id: String,
    val medium: String,
    val resourceUrl: String,
    val small: String,
    val subjectId: String,
    val title: String
)

data class Video(
    val needPay: Boolean,
    val sampleLink: String,
    val source: Source,
    val videoId: String
)

data class Author(
    val alt: String,
    val avatar: String,
    val id: String,
    val name: String,
    val signature: String,
    val uid: String
)

data class ReviewRating(
    val max: Int,
    val min: Int,
    val value: Double
)



data class Source(
    val literal: String,
    val name: String,
    val pic: String
)

data class MovieDetail(val detailDto: MovieDetailDto) {
    val title: String = detailDto.title
    val originalTitle: String = "${detailDto.originalTitle} (${detailDto.year})"
    val basicInfo = "${detailDto.countries[0]} / ${detailDto.genres.joinToString(" ")} / 上映时间：${detailDto.pubdates[0]} / 片长: ${detailDto.durations[0]}"
    val poster = detailDto.images.medium
}
