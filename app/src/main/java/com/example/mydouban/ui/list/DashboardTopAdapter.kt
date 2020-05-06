package com.example.mydouban.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.TopDashboardItemBinding
import com.example.mydouban.model.MovieSubject

class DashboardTopAdapter :
    RecyclerView.Adapter<DashboardTopAdapter.DashBoardListViewHolder>() {

    private var movieTop6: MutableList<MovieSubject> = mutableListOf()
    private var movieInTheater: MutableList<MovieSubject> = mutableListOf()

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

    override fun getItemCount() = movieTop6.size

    override fun onBindViewHolder(holder: DashBoardListViewHolder, position: Int) {
        holder.bind(movieTop6[position])
    }

    fun updateData(newMovieSubjects: List<MovieSubject>) {
        this.movieTop6.clear()
        this.movieTop6.addAll(newMovieSubjects)
        notifyDataSetChanged()
    }


    inner class DashBoardListViewHolder(private val dataBinding: TopDashboardItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(movie: MovieSubject) {
            println("DashBoardListViewHolder bind $movie")
            dataBinding.movieSubject = movie
        }
    }
}
