package com.example.mydouban.repository.local

import android.content.Context
import com.example.mydouban.MyApplication
import com.example.mydouban.common.GsonUtil
import com.example.mydouban.model.MovieSubject


class SharedPreferencesStorage {

    companion object {
        val FILE_MOVIE_TOP_250 = "file_movie_top_250"

        private fun putString(name: String, key: String, value: String) {
            MyApplication.getContext().getSharedPreferences(name, Context.MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .apply()
        }

        private fun getString(name: String, key: String): String? {
            return MyApplication.getContext().getSharedPreferences(name, Context.MODE_PRIVATE)
                .getString(key, "")
        }


        fun getMovieSubjects(start: Int): List<MovieSubject> {
            val movieSubjects = mutableListOf<MovieSubject>()
            var key = start + 1
            while (key <= start + 20) {
                val movieSubjectStr = getString(FILE_MOVIE_TOP_250, key.toString())
                movieSubjectStr?.let {
                    if (movieSubjectStr.isNotEmpty()) {
                        movieSubjects.add(GsonUtil.parseJson(movieSubjectStr))
                    }
                }
                key++
            }
            return movieSubjects
        }


        fun putMovieSubjects(movieSubjects: List<MovieSubject>) {
            movieSubjects.forEach {
                putString(FILE_MOVIE_TOP_250, it.ranking.toString(), GsonUtil.toJson(it))
            }
        }

    }
}