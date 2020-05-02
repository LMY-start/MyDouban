package com.example.mydouban.ui.list

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.repository.MovieTopRepository

class TopListViewModel(application: Application) : AndroidViewModel(application) {

    val movieSubjectsTop250 = MutableLiveData<List<MovieSubject>>()
    private val repository = MovieTopRepository()

    fun getMovieTop250(activity: Activity) {
        repository.getMovieTop250(activity) { subjects ->
            subjects.forEach {
                repository.getMoviePhoto(it.id) { photos ->
                    it.photos.addAll(photos)
                }
            }
            movieSubjectsTop250.postValue(subjects)
        }
    }

    fun isAllPhotoLoaded(subjects: List<MovieSubject>): MovieSubject? {
        return subjects.firstOrNull { it.photos.size == 0 }
    }
}
