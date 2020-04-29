package com.example.mydouban.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.MovieTopItemBinding
import com.example.mydouban.model.MovieSubject

class MovieTopAdapter :
    RecyclerView.Adapter<MovieTopAdapter.MovieTopViewHolder>() {

    private var movies: MutableList<MovieSubject> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTopViewHolder {
        return MovieTopViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.movie_top_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieTopViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun updateData(newMovieSubjects: List<MovieSubject>) {
        println("=============== updateData +++++++++++++++++")
        this.movies.clear()
        this.movies.addAll(newMovieSubjects)
        notifyDataSetChanged()
    }


    inner class MovieTopViewHolder(val dataBinding: MovieTopItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(movie: MovieSubject) {
            println("MovieTopViewHolder bind $movie")
            dataBinding.movieSubject = movie
        }
    }
}
