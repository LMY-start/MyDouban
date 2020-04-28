package com.example.mydouban.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.model.MovieDetailDto
import com.example.mydouban.repository.MovieDetailRepository

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val detailLiveData = MutableLiveData<MovieDetailDto>()
    private val repository = MovieDetailRepository()

    fun getMovieDetail(id: String) {
        repository.getMovieDetail(id) { detailDto ->
            detailLiveData.postValue(detailDto)
        }
    }
}