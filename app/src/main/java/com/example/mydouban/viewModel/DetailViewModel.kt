package com.example.mydouban.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.model.MovieDetail
import com.example.mydouban.repository.MovieDetailRepository

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val detailLiveData = MutableLiveData<MovieDetail>()
    private val repository = MovieDetailRepository()

    fun getMovieDetail(id: String) {
        repository.getMovieDetail(id) { detail ->
            detailLiveData.postValue(detail)
        }
    }
}