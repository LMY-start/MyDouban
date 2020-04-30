package com.example.mydouban.ui.detail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mydouban.R

object ImageViewAttrAdapter {

    @JvmStatic
    @BindingAdapter("playSource")
    fun loadPlaySource(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_detail_play)
            .error(R.drawable.ic_detail_play)
            .into(imageView)
    }
}