package com.example.mydouban.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object MoviePosterAdapter {

    @JvmStatic
    @BindingAdapter("poster")
    fun loadMoviePoster(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .into(imageView)
        }
    }
}