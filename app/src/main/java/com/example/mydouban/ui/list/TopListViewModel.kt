package com.example.mydouban.ui.list

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.model.MovieSubjectDetail
import com.example.mydouban.repository.MovieTopRepository

class TopListViewModel(application: Application) : AndroidViewModel(application) {

    val movieSubjectsTop250 = MutableLiveData<List<MovieSubject>>()
    private val repository = MovieTopRepository()

    fun getMovieTop250(activity: Activity) {
        repository.getMovieTop250(activity) { subjects ->
            for (subject in subjects) {
                repository.getMovieSubjectDetail(subject.id) { movieSubjectDetail ->
                    updateData(subjects, movieSubjectDetail)
                    movieSubjectsTop250.postValue(subjects)
                }
            }
        }
    }

    private fun updateData(subjects: List<MovieSubject>, newMovie: MovieSubjectDetail) {
        var i = 0
        while (i < subjects.size) {
            if (subjects[i].id == newMovie.id) break else i++
        }
        if (i < subjects.size) {
            val movie = subjects[i]
            movie.photos.clear()
            movie.photos.addAll(newMovie.photos)
            movie.describe = newMovie.describe
            movie.ranking = movie.start+i+1
        }
    }
}
