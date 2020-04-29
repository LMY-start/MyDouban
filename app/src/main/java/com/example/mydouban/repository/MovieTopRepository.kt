package com.example.mydouban.repository

import com.example.mydouban.model.MovieTopPageable
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call

class MovieTopRepository {
    companion object {
        private const val DETAIL_URL =
            "https://api.douban.com/v2/movie/top250?apikey=0b2bdeda43b5688921839c8ecb20399b"
    }

    fun getMovieTop(onSuccess: (top: MovieTopPageable) -> Unit) {
        OkHttpUtils.get().url(DETAIL_URL).build().execute(object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                val gson = GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()

                var top = gson.fromJson(response, MovieTopPageable::class.java)
                println("http getMovieTop onResponse ${top.title}")
                onSuccess(top)
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
                println("http getMovieTop onError: $e")
            }
        })
    }

    fun getMovieTopTest(onSuccess: (top: String) -> Unit) {
        OkHttpUtils.get().url(DETAIL_URL).build().execute(object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                println(response)
                onSuccess(response.toString())
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
                println("http getMovieTop onError: $e")
            }
        })
    }
}