package com.example.mydouban.common

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

class GsonUtil {

    companion object {
        inline fun <reified T> parseJson(jsonString: String?): T {
            return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
                .fromJson(jsonString, T::class.java)
        }

        inline fun <reified T> toJson(src: T): String {
            return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
                .toJson(src, T::class.java)
        }
    }

}