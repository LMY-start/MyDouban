package com.example.mydouban.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.R
import com.example.mydouban.model.MovieDetail
import com.example.mydouban.repository.MovieDetailRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val context: Context = application.applicationContext
    val detailLiveData = MutableLiveData<MovieDetail>()
    private val repository = MovieDetailRepository()

    fun getMovieDetail(id: String) {
        repository.getMovieDetail(id) { detailDto ->
            println("detailDto$detailDto")
            val movieDetail = MovieDetail(detailDto)
            detailDto.casts.forEach { it.role = context.getString(R.string.cast) }
            detailDto.directors.forEach { it.role = context.getString(R.string.director) }
            movieDetail.casts.addAll(detailDto.directors)
            movieDetail.casts.addAll(detailDto.casts)

            detailLiveData.postValue(movieDetail)
        }
    }
}