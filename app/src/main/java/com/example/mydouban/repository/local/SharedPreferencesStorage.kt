package com.example.mydouban.repository.local

import android.app.Activity
import android.content.Context
import com.example.mydouban.common.GsonUtil
import com.example.mydouban.model.MovieSubject


class SharedPreferencesStorage {

    companion object {
        val FILE_MOVIE_TOP_250 = "file_movie_top_250"

        private fun putString(activity: Activity, name: String, key: String, value: String) {
            activity.getSharedPreferences(name, Context.MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .apply()
        }

        private fun getString(activity: Activity, name: String, key: String): String? {
            return activity.getSharedPreferences(name, Context.MODE_PRIVATE)
                .getString(key, "")
        }


        fun getMovieSubjects(activity: Activity, start: Int): List<MovieSubject> {
            val movieSubjects = mutableListOf<MovieSubject>()
            var key = start + 1
            while (key <= start + 20) {
                val movieSubjectStr = getString(activity, FILE_MOVIE_TOP_250, key.toString())
                movieSubjectStr?.let {
                    if (movieSubjectStr.isNotEmpty()) {
                        movieSubjects.add(GsonUtil.parseJson(movieSubjectStr))
                    }
                }
                key++
            }
            return movieSubjects
        }


        fun putMovieSubjects(activity: Activity, movieSubjects: List<MovieSubject>) {
            movieSubjects.forEach {
                putString(activity, FILE_MOVIE_TOP_250, it.ranking.toString(), GsonUtil.toJson(it))
            }
        }

    }

}