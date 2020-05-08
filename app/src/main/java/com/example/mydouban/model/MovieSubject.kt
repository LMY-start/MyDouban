package com.example.mydouban.model

import com.example.mydouban.repository.local.entity.Collect
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

class MovieSubject {
    lateinit var id: String
    lateinit var images: Images
    lateinit var rating: Rating
    lateinit var title: String
    val photos = mutableListOf<Photo>()
    var describe = ""
    var start = 0
    var ranking = 0

    constructor()

    constructor(movieSubjectDto: MovieSubjectDto) : this() {
        id = movieSubjectDto.id
        images = movieSubjectDto.images
        rating = movieSubjectDto.rating
        title = movieSubjectDto.title
    }

    fun toCollect(): Collect {
        val trim = describe.split("/").stream().map { it.substring(0, it.lastIndex) }
            .collect(Collectors.toList())
        val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
        return Collect(
            id.toLong(), title, images.medium,
            trim[0].toInt(), rating.average, trim[1].toString(),
            trim[2].toString(), trim[3].toString(), trim[4].toString(),
            now
        )
    }

    companion object {
        fun getEmptyMovieSubject(): MovieSubject {
            return MovieSubject()
        }
    }

}