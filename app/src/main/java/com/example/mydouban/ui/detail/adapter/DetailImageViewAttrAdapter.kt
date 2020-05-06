package com.example.mydouban.ui.detail.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.mydouban.R

object DetailImageViewAttrAdapter {
    @JvmStatic
    @BindingAdapter("castAvatar")
    fun loadCastAvatar(imageView: ImageView, url: String?) {
//        todo: replace placeHolder & error image
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("commentAvatar")
    fun loadCommentAvatar(imageView: ImageView, url: String?) {
//        todo: replace placeHolder & error image
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
    }
}