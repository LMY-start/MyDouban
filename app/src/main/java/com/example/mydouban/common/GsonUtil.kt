package com.example.mydouban.common

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

class GsonUtil {

    companion object {
        inline fun <reified T> parseJson(jsonString: String?): T {
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            return gson.fromJson(jsonString, T::class.java)
        }
    }

}