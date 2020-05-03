package com.example.mydouban.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.TopListRecycleItemBinding
import com.example.mydouban.model.MovieSubjectDetail

class TopListAdapter() : RecyclerView.Adapter<TopListAdapter.TopListViewHolder>() {

    private val movies: MutableList<MovieSubjectDetail> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopListViewHolder {
        return TopListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.top_list_recycle_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: TopListViewHolder, position: Int) {
        holder.bind(movies[position])
    }


    fun updateData(newMovie: MovieSubjectDetail) {
        val lastIndex = this.movies.lastIndex
        this.movies.add(newMovie)
        notifyItemChanged(lastIndex)
    }

    inner class TopListViewHolder(private val dataBinding: TopListRecycleItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(movie: MovieSubjectDetail) {
            println("TopListViewHolder bind $movie")
            dataBinding.movieSubjectDetail = movie
        }
    }

}