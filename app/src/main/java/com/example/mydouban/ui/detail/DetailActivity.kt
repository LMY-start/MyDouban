package com.example.mydouban.ui.detail

import android.graphics.Color
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
import com.example.mydouban.model.MovieDetail
import com.example.mydouban.model.MovieDetailDto
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
    private lateinit var detail: MovieDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getMovieDetail()
        onScrollChangeListener()

        wishBtn.setOnClickListener {
            if (detail.isCollected) return@setOnClickListener
            detailViewModel.collectMovie(detail)
           setWishBtnStyle()
        }
    }

    private fun getMovieDetail() {
        detailViewModel.detailLiveData.observe(this, Observer { detail ->
            this.detail = detail
            movieTitle = detail.title
            summary.text = detail.summary
            headerBinding?.detail = detail
            ratingBinding?.ratingDetail = detail.ratingDetail

            renderVideos()
            bindTags()
            bindCasts()
            bindComments()
            setWishBtnStyle()
        })

        detailViewModel.getMovieDetail("1292226")
    }

    private fun bindTags() {
        if (detail.tags.isNotEmpty()) {
            tagListView.visibility = View.VISIBLE
            tagListView.layoutManager = horizontalLinearLayoutManager
            tagListView.adapter = TagsAdapter(detail.tags)
        }
    }

    private fun renderVideos() {
        if (detail.videos.isNotEmpty()) {
            detailOnlinePlays.visibility = View.VISIBLE
            for (video: MovieDetailDto.Video in detail.videos) {
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

    private fun bindCasts() {
        castListView.layoutManager = horizontalLinearLayoutManager
        castListView.adapter = CastsAdapter(detail.casts)
    }

    private fun bindComments() {
        commentListView.layoutManager = LinearLayoutManager(this)
        commentListView.adapter = CommentsAdapter(detail.comments)
        commentListView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }

    private fun onScrollChangeListener() {
        detailScrollView.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int ->
            val titleBottomPosition = detailTitleView.y + detailTitleView.height
            appBarTitle.text =
                if (scrollY >= titleBottomPosition) movieTitle else resources.getString(R.string.detail_app_bar)
        }
    }

    private fun setWishBtnStyle() {
        if (detail.isCollected) {
            wishBtnText.text = getString(R.string.detail_wish_btn_clicked)
            wishBtnIcon.visibility = View.GONE
            wishBtnText.setTextColor(Color.WHITE)
            wishBtn.background.setTint(getColor(R.color.detailGreyLight))
        }
    }

}
