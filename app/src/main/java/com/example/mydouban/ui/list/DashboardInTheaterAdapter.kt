package com.example.mydouban.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.TopDashboardItemBinding
import com.example.mydouban.model.MovieSubject

class DashboardInTheaterAdapter :
    RecyclerView.Adapter<DashboardInTheaterAdapter.DashBoardListViewHolder>() {

    private var movieInTheater: MutableList<MovieSubject> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

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

    override fun getItemCount() = movieInTheater.size

    override fun onBindViewHolder(holder: DashBoardListViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                onItemClickListener!!.onItemClick(movieInTheater[holder.layoutPosition])
            }
        }
        holder.bind(movieInTheater[position])
    }

    fun updateData(newMovieSubjects: List<MovieSubject>) {
        this.movieInTheater.clear()
        this.movieInTheater.addAll(newMovieSubjects)
        notifyDataSetChanged()
    }


    inner class DashBoardListViewHolder(private val dataBinding: TopDashboardItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(movie: MovieSubject) {
            println("movieInTheater DashBoardListViewHolder bind $movie")
            dataBinding.movieSubject = movie
        }
    }
}
