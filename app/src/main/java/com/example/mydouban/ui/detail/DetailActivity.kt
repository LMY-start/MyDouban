package com.example.mydouban.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.mydouban.R
import com.example.mydouban.viewModel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel by lazy { DetailViewModel(this.application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getMovieDetail()
    }

    private fun getMovieDetail() {
        detailViewModel.detailLiveData.observe(this, Observer { detail ->
            println("detail: $detail")
        })
        detailViewModel.getMovieDetail("25924056")
    }
}
