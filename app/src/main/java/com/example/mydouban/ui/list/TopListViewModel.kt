package com.example.mydouban.ui.list

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.common.update
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.model.MovieSubjectDetail
import com.example.mydouban.repository.local.SharedPreferencesStorage
import com.example.mydouban.repository.remote.MovieRepository

class TopListViewModel(application: Application) : AndroidViewModel(application) {

    private var isLoading = false
    private var currentStart = 0
    val movieSubjectsTop250 = MutableLiveData<List<MovieSubject>>()
    private val repository = MovieRepository()

    fun getMovieTop250(activity: Activity) {
        repository.getMovieTop250(
            activity, currentStart,
            onSuccessLocal = { subjects ->
                movieSubjectsTop250.postValue(subjects)
                isLoading = false
            },
            onSuccessRemote = { subjects ->
                val currentStartTmp = currentStart
                var ranking = 1
                for (subject in subjects) {
                    subject.ranking = currentStartTmp + ranking
                    ranking++
                    repository.getMovieSubjectDetail(subject.id) { movieSubjectDetail ->
                        val singleList = updateData(subjects, movieSubjectDetail)
                        SharedPreferencesStorage.putMovieSubjects(activity, singleList)
                        movieSubjectsTop250.update(singleList)
                    }
                }
                SharedPreferencesStorage.putMovieSubjects(activity, subjects)
                movieSubjectsTop250.update(subjects)
                isLoading = false
            })
    }


    private fun updateData(
        subjects: List<MovieSubject>,
        newMovie: MovieSubjectDetail
    ): List<MovieSubject> {
        val movieSubject = subjects.first { it.id == newMovie.id }
        movieSubject.let {
            it.photos.clear()
            it.photos.addAll(newMovie.photos.subList(0, 4))
            it.describe = newMovie.describe
        }
        return listOf(movieSubject)
    }

    fun loadMore(activity: Activity) {
        currentStart += 20;
        println("+++++ load more $currentStart")
        if (isLoading) return
        isLoading = true
        getMovieTop250(activity)
    }
}
