package com.example.mydouban.repository.remote

import android.app.Activity
import com.example.mydouban.common.GsonUtil
import com.example.mydouban.repository.local.FileStorage
import com.example.mydouban.model.*
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call
import java.io.File

class MovieRepository {
    companion object {
        private const val TOP_250_URL =
            "https://api.douban.com/v2/movie/top250?apikey=0b2bdeda43b5688921839c8ecb20399b"

        private const val IN_THEATERS_URL =
            "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b"

        private const val SUBJECT_DETAIL_URL =
            "https://api.douban.com/v2/movie/subject/%s?apikey=0b2bdeda43b5688921839c8ecb20399b"

        private const val FILE_MOVIE_TOP_250 = "file_movie_top_250"
    }

    fun getMovieTop250(activity: Activity, onSuccess: (subjects: List<MovieSubject>) -> Unit) {
        if (FileStorage.isFileExits(activity, FILE_MOVIE_TOP_250)) {
            val readContent = FileStorage.read(activity, FILE_MOVIE_TOP_250)
            onSuccess(
                GsonUtil.parseJson<MoviePageable>(readContent).subjects
                    .map { MovieSubject(it) }.toList()
            )
        } else {
            getMovieTopAndSaveToFile(
                activity,
                "$TOP_250_URL&start=0",
                FILE_MOVIE_TOP_250,
                onSuccess
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

    private fun getMovieTopAndSaveToFile(
        activity: Activity,
        url: String,
        fileName: String,
        onSuccess: (subjects: List<MovieSubject>) -> Unit
    ) {
        OkHttpUtils.get().url(url).build().execute(object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                response?.let {
                    FileStorage.write(activity, fileName, response)
                    val subjects = GsonUtil.parseJson<MoviePageable>(response).subjects
                        .map { MovieSubject(it) }.toList()
                    onSuccess(subjects)
                }
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
                println("http getMovieTop onError: $e")
            }
        })
    }
}