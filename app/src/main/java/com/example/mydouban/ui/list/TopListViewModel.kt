package com.example.mydouban.ui.list

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.common.update
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.model.MovieSubjectDetail
import com.example.mydouban.repository.remote.MovieRepository

class TopListViewModel(application: Application) : AndroidViewModel(application) {

    val movieSubjectsTop250 = MutableLiveData<List<MovieSubject>>()
    private val repository = MovieRepository()

    fun getMovieTop250(activity: Activity) {
        repository.getMovieTop250(activity) { subjects ->
            var ranking = 1
            for (subject in subjects) {
                subject.ranking = subject.start + ranking
                ranking++
                repository.getMovieSubjectDetail(subject.id) { movieSubjectDetail ->
                    val singleList = updateData(subjects, movieSubjectDetail)
                    movieSubjectsTop250.update(singleList)
                }
            }
            movieSubjectsTop250.postValue(subjects)
        }
    }

    private fun updateData(
        subjects: List<MovieSubject>,
        newMovie: MovieSubjectDetail
    ): List<MovieSubject> {
        val movieSubject = subjects.first { it.id == newMovie.id }
        movieSubject.let {
            it.photos.clear()
            it.photos.addAll(newMovie.photos)
            it.describe = newMovie.describe
        }
        return listOf(movieSubject)
    }
}
