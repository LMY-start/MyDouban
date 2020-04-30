package com.example.mydouban.model

data class RatingDetail(
    var rating: Rating,
    var wishCount: Int,
    val collectCount: Int,
    val ratingsCount: Int
) {
    val stars = rating.average / 10 * 5

    val wishCountText: String get() = "${formatPeopleCount(wishCount)}想看"
    val collectCountText: String get() = "${formatPeopleCount(collectCount)}看过"
    val ratingsCountText: String get() = "${ratingsCount}人评分"

    private fun formatPeopleCount(count: Int): String =
        if (count >= 10000) "${String.format(
            "%.1f",
            count / 10000.0
        )}万人" else "${count.toString()}人"
}