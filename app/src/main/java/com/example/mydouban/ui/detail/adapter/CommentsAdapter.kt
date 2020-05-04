package com.example.mydouban.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.DetailCommentBinding
import com.example.mydouban.model.PopularComment

class CommentsAdapter(private val comments: List<PopularComment>)
    : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.detail_comment,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    inner class CommentViewHolder(val databinding: DetailCommentBinding) :
        RecyclerView.ViewHolder(databinding.root) {

        fun bind(comment: PopularComment) {
            databinding.comment = comment
        }
    }
}