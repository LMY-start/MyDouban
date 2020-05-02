package com.example.mydouban.common

import com.example.mydouban.model.MovieTopPageable
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

class GsonUtil {

    companion object {
        fun parseMovieTopPageable(jsonString: String): MovieTopPageable {
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            return gson.fromJson(jsonString, MovieTopPageable::class.java)
        }
    }

}