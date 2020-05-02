package com.example.mydouban.model

class MovieSubject(movieSubjectDto: MovieSubjectDto) {
    val id: String = movieSubjectDto.id
    val images: Images = movieSubjectDto.images
    val rating: Rating = movieSubjectDto.rating
    val title: String = movieSubjectDto.title
    val year: String = movieSubjectDto.year
    val photos: MutableList<String> = mutableListOf()
}