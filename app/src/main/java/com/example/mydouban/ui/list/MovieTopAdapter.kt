package com.example.mydouban.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.MovieTopItemBinding
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.model.MovieTopPageable
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.InputStreamReader

class MovieTopAdapter(val context: Context) :
    RecyclerView.Adapter<MovieTopAdapter.MovieTopViewHolder>() {

    private var movies: List<MovieSubject> = readFromFile()


    fun readFromFile(): List<MovieSubject> {
        val fileName = "myFile"
        var list = listOf<MovieSubject>()
        BufferedReader(InputStreamReader(context.openFileInput(fileName))).use {
            val readText = it.readText()
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
            var top = gson.fromJson(readText, MovieTopPageable::class.java)
            list = top.subjects.subList(10, 16)
        }
        return list
    }

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

    fun updateData(newData: List<MovieSubject>) {
        println("=============== updateData +++++++++++++++++")
//        movies = newData
//        notifyDataSetChanged()
    }


    inner class MovieTopViewHolder(val dataBinding: MovieTopItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(movie: MovieSubject) {
            println("MovieTopViewHolder bind $movie")
            dataBinding.movieSubject = movie
//            val screenWidth: Int = ScreenUtils.getScreenWidth(mContext) //屏幕宽度

//            itemView.width = itemView.
        }
    }
}
