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
import com.example.mydouban.ui.detail.adapter.CastsAdapter
import com.example.mydouban.ui.detail.adapter.CommentsAdapter
import com.example.mydouban.ui.detail.adapter.DetailImageViewAttrAdapter
import com.example.mydouban.ui.detail.adapter.TagsAdapter
import com.example.mydouban.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_header.*
import kotlinx.android.synthetic.main.detail_online_plays.*
import kotlinx.android.synthetic.main.detail_rating.*
import kotlin.math.min

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

        loadDetail()
        handelMovieDetailRes()

        onScrollChangeListener()
        onWishBtnCLick()
        onToolBarNavigationIconClick()
    }

    private fun loadDetail() {
        detailStateView.showLoading(R.string.is_loading)
        detailViewModel.getMovieDetail(intent.getStringExtra("id"))
    }

    private fun handelMovieDetailRes() {
        detailViewModel.detailLiveData.observe(this, Observer { detail ->
            detailStateView.showContent()
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

        detailViewModel.errorLiveData.observe(this, Observer { e ->
            detailStateView.showError(R.string.is_failed) {
                loadDetail()
            }
        })
    }

    private fun bindTags() {
        if (detail.tags.isNotEmpty()) {
            tagListView.visibility = View.VISIBLE
            tagListView.layoutManager = horizontalLinearLayoutManager
            tagListView.adapter = TagsAdapter(detail.tags)
        }
    }

    private fun renderVideos() {
        val videos = detail.videos
        if (videos.isNotEmpty()) {
            detailOnlinePlays.visibility = View.VISIBLE
            playIconWrapper.removeAllViews()
            for (i in 0 until min(3, videos.size)) {
                renderVideoSourcePic(videos[i].source.pic)

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
            detailToolbar.title =
                if (scrollY >= titleBottomPosition) movieTitle else resources.getString(R.string.detail_app_bar)
        }
    }

    private fun onWishBtnCLick() {
        wishBtn.setOnClickListener {
            if (detail.isCollected) return@setOnClickListener
            detailViewModel.collectMovie(detail)
            setWishBtnStyle()
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

    private fun onToolBarNavigationIconClick() {
        detailToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
