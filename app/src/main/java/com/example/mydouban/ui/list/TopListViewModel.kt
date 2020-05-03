package com.example.mydouban.ui.list

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.model.MovieSubjectDetail
import com.example.mydouban.repository.MovieTopRepository

class TopListViewModel(application: Application) : AndroidViewModel(application) {

    val movieSubjectsTop250 = MutableLiveData<MovieSubjectDetail>()
    private val repository = MovieTopRepository()

    fun getMovieTop250(activity: Activity) {
        repository.getMovieTop250(activity) { subjects ->
            subjects.forEach {
                repository.getMovieSubjectDetail(it.id) { movieSubjectDetail ->
                    movieSubjectsTop250.postValue(movieSubjectDetail)
                }
            }
        }
    }
}
