package com.example.mydouban.repository

import android.app.Activity
import com.example.mydouban.common.GsonUtil
import com.example.mydouban.database.storage.FileStorage
import com.example.mydouban.model.MoviePhoto
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.model.MovieTopPageable
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call
import java.io.File

class MovieTopRepository {
    companion object {
        private const val TOP_250_URL =
            "https://api.douban.com/v2/movie/top250?apikey=0b2bdeda43b5688921839c8ecb20399b"

        private const val PHOTO_URL =
            "https://api.douban.com/v2/movie/subject/%s/photos?apikey=0b2bdeda43b5688921839c8ecb20399b"

        private const val FILE_MOVIE_TOP_250 = "movie_top_250"
    }

    fun getMovieTop250(activity: Activity, onSuccess: (subjects: List<MovieSubject>) -> Unit) {
        if (File(FILE_MOVIE_TOP_250).exists()) {
            val readContent = FileStorage.read(activity, FILE_MOVIE_TOP_250)
            GsonUtil.parseMovieTopPageable(readContent)
            onSuccess(
                GsonUtil.parseMovieTopPageable(readContent).subjects
                    .map { MovieSubject(it) }.toList()
            )
        } else {
            getMovieTopAndSaveToFile(activity, TOP_250_URL, onSuccess)

        }
    }

    fun getMovieTop6(onSuccess: (subjects: List<MovieSubject>) -> Unit) {
        getMovieTop("$TOP_250_URL&count=6", onSuccess)
    }


    fun getMoviePhoto(movieSubjectId: String, onSuccess: (thumb: MutableList<String>) -> Unit) {
        println(" url ======${String.format(PHOTO_URL, movieSubjectId)}")
        OkHttpUtils.get().url(String.format(PHOTO_URL, movieSubjectId)).build()
            .execute(object : StringCallback() {
                override fun onResponse(response: String?, id: Int) {
                    val gson = GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()

                    val top = gson.fromJson(response, MoviePhoto::class.java)
                    val thumbs = mutableListOf<String>()
                    println(" ======top.photos.size =======${top.photos.size}")
                    top.photos.subList(0, 4).forEach {
                        thumbs.add(it.thumb)
                    }
                    onSuccess(thumbs)
                }

                override fun onError(call: Call?, e: Exception?, id: Int) {
                    println("http getMovieTop onError: $e")
                }
            })
    }


    private fun getMovieTop(url: String, onSuccess: (subjects: List<MovieSubject>) -> Unit) {
        OkHttpUtils.get().url(url).build().execute(object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                val gson = GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()

                val top = gson.fromJson(response, MovieTopPageable::class.java)
                val subjects = top.subjects.map { MovieSubject(it) }.toList()
                onSuccess(subjects)
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
                println("http getMovieTop onError: $e")
            }
        })
    }

    private fun getMovieTopAndSaveToFile(
        activity: Activity, url: String, onSuccess: (subjects: List<MovieSubject>) -> Unit
    ) {
        OkHttpUtils.get().url(url).build().execute(object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                response?.let {
                    FileStorage.write(activity, FILE_MOVIE_TOP_250, response)
                    val subjects = GsonUtil.parseMovieTopPageable(response).subjects
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