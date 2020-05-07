package com.example.mydouban.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mydouban.R
import com.example.mydouban.model.MovieDetail
import com.example.mydouban.model.MovieDetailDto
import com.example.mydouban.repository.MovieDetailRepository
import com.example.mydouban.repository.ResponseCallBack
import com.example.mydouban.repository.local.dao.CollectDaoOperation
import com.example.mydouban.repository.local.entity.Collect
import java.lang.Exception
import java.util.*

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val context: Context = application.applicationContext
    val detailLiveData = MutableLiveData<MovieDetail>()
    val errorLiveData = MutableLiveData<Exception>()
    private val repository = MovieDetailRepository()
    private val collectDaoInstance by lazy { CollectDaoOperation.getInstance() }

    fun getMovieDetail(id: String) {
        repository.getMovieDetail(id, object : ResponseCallBack<MovieDetailDto> {
            override fun onSuccess(detail: MovieDetailDto) {
                this@DetailViewModel.onSuccess(detail)
            }

            override fun onError(e: Exception?) {
                e?.printStackTrace()
                this@DetailViewModel.errorLiveData.postValue(e)
            }
        })
    }

    fun onSuccess(detailDto: MovieDetailDto) {
        val movieDetail = MovieDetail(detailDto)

        detailDto.casts.forEach { it.role = context.getString(R.string.cast) }
        detailDto.directors.forEach { it.role = context.getString(R.string.director) }
        movieDetail.casts.addAll(detailDto.directors)
        movieDetail.casts.addAll(detailDto.casts)

        val collectedMovie = collectDaoInstance.queryForTitle(context, detailDto.title)
        movieDetail.isCollected = !collectedMovie.isNullOrEmpty()
                && collectedMovie.find { it.id.toString() == movieDetail.id } != null

        detailLiveData.postValue(movieDetail)
    }

    fun collectMovie(detail: MovieDetail) {
        val genres = detail.genres.joinToString("、")
        val directors = detail.casts
            .filter { it.role == context.getString(R.string.director) }
            .joinToString(",", "", "", -1, "...") { it.name }
        val casts = detail.casts
            .filter { it.role == context.getString(R.string.cast) }
            .joinToString(",", "", "", -1, "...") { it.name }

        val collect = Collect(
            detail.id.toLong(),
            detail.title,
            detail.poster,
            detail.year,
            detail.ratingDetail.rating.average,
            detail.country,
            genres,
            directors,
            casts,
            Calendar.getInstance().get(Calendar.DAY_OF_YEAR).toString()
        )
        collectDaoInstance.insertData(context, collect)
        detail.isCollected = true
    }
}