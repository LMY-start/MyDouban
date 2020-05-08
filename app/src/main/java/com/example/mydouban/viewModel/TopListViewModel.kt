package com.example.mydouban.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.common.update
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.model.MovieSubjectDetail
import com.example.mydouban.repository.local.SharedPreferencesStorage
import com.example.mydouban.repository.remote.MovieRepository

class TopListViewModel(application: Application) : AndroidViewModel(application) {

    var isLoading = false
    private var currentStart = 0
    val movieSubjectsTop250 = MutableLiveData<List<MovieSubject>>()
    private val repository = MovieRepository()

    fun getMovieTop250() {
        repository.getMovieTop250(
            currentStart, onSuccessLocal = { subjects ->
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

                        SharedPreferencesStorage.putMovieSubjects(singleList)
                        movieSubjectsTop250.update(singleList)
                    }
                }
                SharedPreferencesStorage.putMovieSubjects(subjects)
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

    fun loadMore() {
        isLoading = true
        currentStart += 20
        println("+++++ load more $currentStart")
        getMovieTop250()
    }
}
