package com.example.mydouban.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydouban.R
import com.example.mydouban.databinding.DetailHeaderBinding
import com.example.mydouban.databinding.DetailOnlinePlaysBinding
import com.example.mydouban.databinding.DetailRatingBinding
import com.example.mydouban.model.Cast
import com.example.mydouban.model.MovieDetail
import com.example.mydouban.model.MovieDetailDto
import com.example.mydouban.model.RatingDetail
import com.example.mydouban.ui.detail.adapter.CastsAdapter
import com.example.mydouban.ui.detail.adapter.TagsAdapter
import com.example.mydouban.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_header.*
import kotlinx.android.synthetic.main.detail_online_plays.*
import kotlinx.android.synthetic.main.detail_rating.*

class DetailActivity : AppCompatActivity() {

    private val detailViewModel by lazy { DetailViewModel(this.application) }
    private val horizontalLinearLayoutManager get() = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    private var movieTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getMovieDetail()
        onScrollChangeListener()
    }

    private fun getMovieDetail() {
        detailViewModel.detailLiveData.observe(this, Observer { detailDto ->
            bindHeader(detailDto)
            bindRating(detailDto)
            bindVideos(detailDto)
            bindTags(detailDto)
            bindCasts(detailDto.directors, detailDto.casts)

            movieTitle = detailDto.title
            summary.text = detailDto.summary
        })
        detailViewModel.getMovieDetail("1292226")
    }

    private fun bindTags(detailDto: MovieDetailDto) {
        tagListView.layoutManager = horizontalLinearLayoutManager
        tagListView.adapter =
            TagsAdapter(detailDto.tags)
    }

    private fun bindVideos(detailDto: MovieDetailDto) {
        if (detailDto.videos.isNotEmpty()) {
            val onlinePlaysBinding =
                DataBindingUtil.bind<DetailOnlinePlaysBinding>(detailOnlinePlays)
            onlinePlaysBinding?.videos = detailDto.videos
        } else {
            detailOnlinePlays.visibility = View.GONE
        }
    }

    private fun bindRating(detailDto: MovieDetailDto) {
        val ratingBinding =
            DataBindingUtil.bind<DetailRatingBinding>(movieDetailRatingView)

        ratingBinding?.ratingDetail = RatingDetail(
            detailDto.rating,
            detailDto.wishCount,
            detailDto.collectCount,
            detailDto.ratingsCount
        )
    }

    private fun bindHeader(detailDto: MovieDetailDto) {
        val headerBinding = DataBindingUtil.bind<DetailHeaderBinding>(detailHeaderView)
        headerBinding?.detail = MovieDetail(detailDto)
    }

    private fun bindCasts(directors: List<Cast>, casts: List<Cast>) {
        castListView.layoutManager = horizontalLinearLayoutManager
        castListView.adapter = CastsAdapter(directors, casts)
    }

    private fun onScrollChangeListener() {
        detailScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            val titleBottomPosition = detailTitleView.y + detailTitleView.height
            appBarTitle.text =
                if (scrollY >= titleBottomPosition) movieTitle else resources.getString(R.string.detail_app_bar)
        }
    }

}
