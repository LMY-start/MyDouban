package com.example.mydouban.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.model.MovieTopPageable
import com.example.mydouban.repository.MovieTopRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val text: LiveData<String> =  MutableLiveData<String>().apply {
        value = "这是new电影列表页面"
    }

    private val movieSubjects = MutableLiveData<List<MovieTopPageable.MovieSubject>>()
    private val repository = MovieTopRepository()

    fun getMovieTop() {
        repository.getMovieTop { movieTop ->
            val top6 = movieTop.subjects.subList(0, 6)
            movieSubjects.postValue(top6)
        }
    }
}

