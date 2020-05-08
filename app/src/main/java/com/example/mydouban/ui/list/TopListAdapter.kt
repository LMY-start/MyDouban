package com.example.mydouban.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mydouban.R
import com.example.mydouban.databinding.TopListRecycleItemBinding
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.model.MovieSubject.Companion.getEmptyMovieSubject


class TopListAdapter : Adapter<ViewHolder>() {

    private val movies: MutableList<MovieSubject> = mutableListOf(getEmptyMovieSubject())
    private var onItemClickListener: OnItemClickListener? = null
    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1
    var canLoadMore = true

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            TYPE_HEADER -> {
                val itemView = LayoutInflater.from(parent.context).inflate(
                    R.layout.top_list_header_view,
                    parent,
                    false
                )
                return TopListHeaderViewHolder(itemView);
            }
            else -> {
                val dataBinding = DataBindingUtil.inflate<TopListRecycleItemBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.top_list_recycle_item,
                    parent, false
                )
                return TopListItemViewHolder(dataBinding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }
        return TYPE_ITEM
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is TopListHeaderViewHolder -> {
            }

            is TopListItemViewHolder -> {
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
        }
    }

    fun updateData(newMovieSubjects: List<MovieSubject>) {
        val newMovies = mutableListOf<MovieSubject>()
        newMovies.addAll(newMovieSubjects)
        if (newMovies.size > 1) {
            var index = movies.lastIndex
            newMovies.forEach {
                index++
                movies.add(index, it)
                notifyItemInserted(index)
            }
            canLoadMore = true
        } else {
            val newMovie = newMovies[0]
            var index = 1
            while (index < movies.size) {
                if (movies[index].id == newMovie.id) break
                index++
            }
            if (index < movies.size) {
                movies[index] = newMovie
            }
            notifyItemChanged(index)
        }
    }

    private inner class TopListHeaderViewHolder(view: View) : ViewHolder(view)

    private inner class TopListItemViewHolder(private val dataBinding: TopListRecycleItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(movie: MovieSubject) {
            dataBinding.movieSubject = movie
        }
    }

}