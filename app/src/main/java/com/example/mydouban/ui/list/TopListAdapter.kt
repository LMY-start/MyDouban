package com.example.mydouban.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.TopListRecycleItemBinding
import com.example.mydouban.model.MovieSubject


class TopListAdapter() : RecyclerView.Adapter<TopListAdapter.TopListViewHolder>() {

    private val movies: MutableList<MovieSubject> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
        fun onItemClickWantWatch(view: View?, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

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
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                onItemClickListener!!.onItemClick(holder.itemView, holder.layoutPosition)
            }
        }

        holder.itemView.findViewById<ViewGroup>(R.id.btn_want_watch).setOnClickListener {
            onItemClickListener ?.let {
                onItemClickListener!!.onItemClickWantWatch(holder.itemView, holder.layoutPosition)
            }
        }

        holder.bind(movies[position])
    }

    fun updateData(newMovies: List<MovieSubject>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    inner class TopListViewHolder(private val dataBinding: TopListRecycleItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(movie: MovieSubject) {
            println("TopListViewHolder bind  ${movie.title}  ${movie.ranking}  ${movie.photos}")
            dataBinding.movieSubject = movie
        }
    }

}