package com.example.mydouban.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.TopDashboardItemBinding
import com.example.mydouban.model.MovieSubject

class DashboardListAdapter :
    RecyclerView.Adapter<DashboardListAdapter.DashBoardListViewHolder>() {

    private var tops: MutableList<MovieSubject> = mutableListOf()
    private var inTheaters: MutableList<MovieSubject> = mutableListOf()

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

    override fun getItemCount() = tops.size

    override fun onBindViewHolder(holder: DashBoardListViewHolder, position: Int) {
        holder.bind(tops[position])
    }

    fun updateData(newMovieSubjects: List<MovieSubject>) {
        this.tops.clear()
        this.tops.addAll(newMovieSubjects)
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
