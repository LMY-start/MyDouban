package com.example.mydouban.model

class MovieSubjectDetail(movieDetailDto: MovieDetailDto) {
    val id: String = movieDetailDto.id
    val images: Images = movieDetailDto.images
    val rating: Rating = movieDetailDto.rating
    val title: String = movieDetailDto.title
    val year: String = movieDetailDto.year
    val casts: List<Cast> = movieDetailDto.casts
    val directors: List<Cast> = movieDetailDto.directors
    val photos: List<Photo> = movieDetailDto.photos
    val genres: List<String> = movieDetailDto.genres
    val countries:List<String> = movieDetailDto.countries
    val describe: String = describe()

    private fun describe(): String {
        val str= String.format(
            "%s / %s / %s / %s / %s",
            year,
            countries[0],
            getGenres(),
            getDirectors(),
            getCasts()
        )
        println("=======describe ${str}")
        return str
    }


    private fun getCasts(): String {
        return combine(casts)
    }

    private fun getDirectors(): String {
        return combine(directors)
    }

    private fun combine(peoples: List<Cast>): String {
        var combineString = ""
        peoples.forEach {
            combineString += "${it.name} "
        }
        return combineString.substring(0,combineString.length - 1)
    }

    private fun getGenres(): String {
        var genreString = ""
        genres.forEach {
            genreString += "$it "
        }
        return genreString.substring(0,genreString.length - 1)
    }
}