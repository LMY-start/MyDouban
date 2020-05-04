package com.example.mydouban.common

import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.mydouban.R

object MoviePosterAdapter {

    @JvmStatic
    @BindingAdapter("poster")
    fun loadMoviePoster(imageView: ImageView, url: String?) {
        //        todo: add placeHolder & error image
        if (url != null) {
            Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("rankingIcon")
    fun loadRankingIcon(button: Button, ranking: Int) {
        val id = when (ranking) {
            1 -> R.drawable.ic_sort_first
            2 -> R.drawable.ic_sort_second
            3 -> R.drawable.ic_sort_third
            else -> R.drawable.ic_sort_other
        }
        button.background = button.context.getDrawable(id)
    }
}