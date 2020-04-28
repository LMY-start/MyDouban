package com.example.mydouban.repository

import com.example.mydouban.model.MovieDetailDto
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.zhy.http.okhttp.OkHttpUtils
import java.lang.Exception
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call

class MovieDetailRepository {
    companion object {
        private const val DETAIL_URL = "https://douban.uieee.com/v2/movie/subject/"
    }

    fun getMovieDetail(id: String, onSuccess: (detail: MovieDetailDto) -> Unit ) {
        OkHttpUtils.get().url(DETAIL_URL + id).build().execute(object : StringCallback() {
            override fun onResponse(response: String?, id: Int) {
                val gson = GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()
                var detail = gson.fromJson(response, MovieDetailDto::class.java)
                onSuccess(detail)
            }

            override fun onError(call: Call?, e: Exception?, id: Int) {
                println("getMovieDetailError: $e")
            }
        })
    }
}