package com.example.mydouban.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object MoviePosterAdapter {

    @JvmStatic
    @BindingAdapter("poster")
    fun loadMoviePoster(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }
}