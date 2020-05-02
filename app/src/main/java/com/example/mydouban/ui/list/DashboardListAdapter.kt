package com.example.mydouban.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.TopDashboardItemBindingImpl
import com.example.mydouban.model.MovieSubject

class DashboardListAdapter :
    RecyclerView.Adapter<DashboardListAdapter.DashBoardListViewHolder>() {

    private var movies: MutableList<MovieSubject> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardListViewHolder {
        return DashBoardListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.top_dashboard_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: DashBoardListViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun updateData(newMovieSubjects: List<MovieSubject>) {
        this.movies.clear()
        this.movies.addAll(newMovieSubjects)
        notifyDataSetChanged()
    }


    inner class DashBoardListViewHolder(private val dataBinding: TopDashboardItemBindingImpl) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(movie: MovieSubject) {
            println("DashBoardListViewHolder bind $movie")
            dataBinding.movieSubject = movie
        }
    }
}
