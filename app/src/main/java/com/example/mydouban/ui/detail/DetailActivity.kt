package com.example.mydouban.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydouban.R
import com.example.mydouban.databinding.DetailHeaderBinding
import com.example.mydouban.databinding.DetailRatingBinding
import com.example.mydouban.model.Cast
import com.example.mydouban.model.MovieDetailDto
import com.example.mydouban.model.PopularComment
import com.example.mydouban.ui.detail.adapter.CastsAdapter
import com.example.mydouban.ui.detail.adapter.CommentsAdapter
import com.example.mydouban.ui.detail.adapter.DetailImageViewAttrAdapter
import com.example.mydouban.ui.detail.adapter.TagsAdapter
import com.example.mydouban.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_header.*
import kotlinx.android.synthetic.main.detail_online_plays.*
import kotlinx.android.synthetic.main.detail_rating.*

class DetailActivity : AppCompatActivity() {

    private val headerBinding by lazy { DataBindingUtil.bind<DetailHeaderBinding>(detailHeaderView) }
    private val ratingBinding by lazy {
        DataBindingUtil.bind<DetailRatingBinding>(movieDetailRatingView)
    }
    private val detailViewModel by lazy { DetailViewModel(this.application) }

    private val horizontalLinearLayoutManager
        get() = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    private var movieTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getMovieDetail()
        onScrollChangeListener()
    }

    private fun getMovieDetail() {
        detailViewModel.detailLiveData.observe(this, Observer { detail ->
            movieTitle = detail.title
            summary.text = detail.summary
            headerBinding?.detail = detail
            ratingBinding?.ratingDetail = detail.ratingDetail

            renderVideos(detail.videos)
            bindTags(detail.tags)
            bindCasts(detail.casts)
            bindComments(detail.comments)

        })

        detailViewModel.getMovieDetail("1292226")
    }

    private fun bindTags(tags: List<String>) {
        if (tags.isNotEmpty()) {
            tagListView.visibility = View.VISIBLE
            tagListView.layoutManager = horizontalLinearLayoutManager
            tagListView.adapter = TagsAdapter(tags)
        }
    }

    private fun renderVideos(videos: List<MovieDetailDto.Video>) {
        if (videos.isNotEmpty()) {
            detailOnlinePlays.visibility = View.VISIBLE
            for (video: MovieDetailDto.Video in videos) {
                renderVideoSourcePic(video.source.pic)
            }
        }
    }

    private fun renderVideoSourcePic(url: String) {
        val imageView = ImageView(this)

        imageView.layoutParams = LinearLayout.LayoutParams(
            resources.getDimension(R.dimen.detail_play_icon).toInt(),
            resources.getDimension(R.dimen.detail_play_icon).toInt()
        )
        val lp = imageView.layoutParams as LinearLayout.LayoutParams
        lp.marginStart = 16

        DetailImageViewAttrAdapter.loadVideoSourcePic(imageView, url)

        playIconWrapper.addView(imageView)
    }

    private fun bindCasts(casts: MutableList<Cast>) {
        castListView.layoutManager = horizontalLinearLayoutManager
        castListView.adapter = CastsAdapter(casts)
    }

    private fun bindComments(comments: List<PopularComment>) {
        commentListView.layoutManager = LinearLayoutManager(this)
        commentListView.adapter = CommentsAdapter(comments)
        commentListView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }

    private fun onScrollChangeListener() {
        detailScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            val titleBottomPosition = detailTitleView.y + detailTitleView.height
            appBarTitle.text =
                if (scrollY >= titleBottomPosition) movieTitle else resources.getString(R.string.detail_app_bar)
        }
    }

}
