package com.example.mydouban.repository.remote

import com.example.mydouban.common.GsonUtil
import com.example.mydouban.model.*
import com.example.mydouban.repository.local.SharedPreferencesStorage
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call

class MovieRepository {
    companion object {
        private const val TOP_250_URL =
            "https://api.douban.com/v2/movie/top250?apikey=0b2bdeda43b5688921839c8ecb20399b"

        private const val IN_THEATERS_URL =
            "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b"

        private const val SUBJECT_DETAIL_URL =
            "https://api.douban.com/v2/movie/subject/%s?apikey=0b2bdeda43b5688921839c8ecb20399b"

    }

    fun getMovieTop250(
        start: Int,
        onSuccessLocal: (subjects: List<MovieSubject>) -> Unit,
        onSuccessRemote: (subjects: List<MovieSubject>) -> Unit
    ) {

        val subjects = SharedPreferencesStorage.getMovieSubjects(start)
        if (subjects.isNotEmpty()) {
            onSuccessLocal(subjects)
        } else {
            getMovies(
                "$TOP_250_URL&start=$start",
                onSuccessRemote
            )
        }
    }

    fun getMovieTop6(onSuccess: (subjects: List<MovieSubject>) -> Unit) {
        getMovies("$TOP_250_URL&count=6", onSuccess)
    }

    fun getMovieInTheater(onSuccess: (subjects: List<MovieSubject>) -> Unit) {
        getMovies("$IN_THEATERS_URL", onSuccess)
    }


    fun getMovieSubjectDetail(
        movieSubjectId: String, onSuccess: (movieSubjectDetail: MovieSubjectDetail) -> Unit
    ) {
        OkHttpUtils.get().url(String.format(SUBJECT_DETAIL_URL, movieSubjectId)).build()
            .execute(object : StringCallback() {
                override fun onResponse(response: String?, id: Int) {
                    val movieSubjectDetail = MovieSubjectDetail(GsonUtil.parseJson(response))
                    onSuccess(movieSubjectDetail)
                }

                override fun onError(call: Call?, e: Exception?, id: Int) {
                    println("http getMovieTop onError: $e")
                }
            })
    }


    private fun getMovies(url: String, onSuccess: (subjects: List<MovieSubject>) -> Unit) {
        OkHttpUtils.get().url(url).build().execute(object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                val subjects = GsonUtil.parseJson<MoviePageable>(response).subjects
                    .map { MovieSubject(it) }.toList()
                onSuccess(subjects)
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
                println("http getMovieTop onError: $e")
            }
        })
    }
}