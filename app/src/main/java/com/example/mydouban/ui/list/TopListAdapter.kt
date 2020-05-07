package com.example.mydouban.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.mydouban.R
import com.example.mydouban.databinding.TopListRecycleItemBinding
import com.example.mydouban.model.MovieSubject


class TopListAdapter() : Adapter<TopListAdapter.TopListViewHolder>() {

    private val movies: MutableList<MovieSubject> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null


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
                onItemClickListener!!.onItemClick(movies[holder.layoutPosition])
            }
        }

        holder.itemView.findViewById<ViewGroup>(R.id.btn_want_watch).setOnClickListener {
            onItemClickListener?.let {
                onItemClickListener!!.onItemClickWantWatch(
                    movies[holder.layoutPosition]
                )
            }
        }

        holder.bind(movies[position])
    }

    fun updateData(newMovieSubjects: List<MovieSubject>) {
        val newMovies = mutableListOf<MovieSubject>()
        newMovies.addAll(newMovieSubjects)
        if (newMovies.size > 1) {
            movies.addAll(newMovies)
            notifyDataSetChanged()
        } else {
            val newMovie = newMovies[0]
            var index = 0
            while (index < movies.size) {
                if (movies[index].id == newMovie.id) break
                index++
            }
            if (index < movies.size) {
                movies[index] = newMovie
                val movie = movies[index]
                println("+++++++++++++++updateData2  ${movie.title} ranking=${newMovies[0].ranking} ${movie.photos.size} , ${newMovie.photos.size}  ${movie.describe}")
            }
            notifyItemChanged(index)
        }
    }

    inner class TopListViewHolder(private val dataBinding: TopListRecycleItemBinding) :
        ViewHolder(dataBinding.root) {
        fun bind(movie: MovieSubject) {
            println("TopListViewHolder bind  ${movie.title}  ${movie.ranking}  ${movie.photos}")
            dataBinding.movieSubject = movie
        }
    }

}