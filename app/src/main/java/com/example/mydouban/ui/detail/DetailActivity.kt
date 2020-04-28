package com.example.mydouban.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mydouban.R
import com.example.mydouban.databinding.MovieDetailHeaderBinding
import com.example.mydouban.model.MovieDetail
import com.example.mydouban.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.movie_detail_header.*

class DetailActivity : AppCompatActivity() {

    private val detailViewModel by lazy { DetailViewModel(this.application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getMovieDetail()
    }

    private fun getMovieDetail() {
        detailViewModel.detailLiveData.observe(this, Observer { detailDto ->
            val binding = DataBindingUtil.bind<MovieDetailHeaderBinding>(detailHeaderView)
            binding?.detail = MovieDetail(detailDto)
        })
        detailViewModel.getMovieDetail("25924056")
    }
}
