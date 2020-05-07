package com.example.mydouban.ui.collect

import android.content.Intent
import android.view.View
import com.example.mydouban.model.Collect
import com.example.mydouban.ui.detail.DetailActivity

class CollectItemEventListener {

    fun onCollectItemClick(view: View, collect: Collect) {

        Intent(view.context, DetailActivity::class.java).also {
            it.putExtra("id", collect.id.toString())
            view.context.startActivity(it)
        }
    }
}