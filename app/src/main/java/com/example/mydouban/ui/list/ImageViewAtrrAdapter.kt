package com.example.mydouban.ui.list

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mydouban.R

object ImageViewAtrrAdapter {

    @JvmStatic
    @BindingAdapter("remote")
    fun loadImage(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView.context)
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.mipmap.ic_launcher))
                .load(url).into(imageView)
        }
    }
}