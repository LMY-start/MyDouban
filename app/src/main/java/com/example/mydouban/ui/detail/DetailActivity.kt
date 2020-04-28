package com.example.mydouban.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mydouban.R
import com.example.mydouban.databinding.ActivityDetailBinding
import com.example.mydouban.model.MovieDetail
import com.example.mydouban.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val detailViewModel by lazy { DetailViewModel(this.application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getMovieDetail()
    }

    private fun getMovieDetail() {
        detailViewModel.detailLiveData.observe(this, Observer { detailDto ->
            val binding = DataBindingUtil.bind<ActivityDetailBinding>(detailLayout)
            binding?.detail = MovieDetail(detailDto)
        })
        detailViewModel.getMovieDetail("25924056")
    }
}
