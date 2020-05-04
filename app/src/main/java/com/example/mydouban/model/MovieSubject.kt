package com.example.mydouban.model

class MovieSubject(movieSubjectDto: MovieSubjectDto) {
    val id: String = movieSubjectDto.id
    val images: Images = movieSubjectDto.images
    val rating: Rating = movieSubjectDto.rating
    val title: String = movieSubjectDto.title
    val photos = mutableListOf<Photo>()
    var describe = ""
    var start = 0
    var ranking = 0
}