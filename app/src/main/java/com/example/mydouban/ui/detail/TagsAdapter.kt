package com.example.mydouban.ui.detail

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R


class TagsAdapter(private var tags: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HEADER_TYPE = 0
        const val ITEM_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_TYPE ->
                TagHeaderViewHolder(TextView(parent.context))

            else ->
                TagViewHolder(TextView(parent.context))
        }
    }

    override fun getItemCount(): Int {
        return tags.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HEADER_TYPE -> {
                (holder as TagHeaderViewHolder).textView.text = "相关分类"
            }
            ITEM_TYPE -> {
                (holder as TagViewHolder).textView.text = tags[position - 1]
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER_TYPE
            else -> ITEM_TYPE
        }
    }

    inner class TagHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView as TextView

        init {
            textView.setPadding(0, 10, 20, 10)
        }
    }

    inner class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView: TextView = itemView as TextView

        init {
            textView.setTextColor(Color.WHITE)

            textView.setPadding(30, 10, 30, 10)

            textView.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val lp = textView.layoutParams as LinearLayout.LayoutParams
            lp.marginStart = 30

            textView.setBackgroundResource(R.drawable.detail_tag_backgroung)
        }
    }
}