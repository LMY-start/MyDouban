package com.example.mydouban.ui.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.DetailCastBinding
import com.example.mydouban.model.Cast

class CastsAdapter(
    private var directors: List<Cast>,
    private var casts: List<Cast>
) : RecyclerView.Adapter<CastsAdapter.CastViewHolder>() {
    private val allCasts = listOf<Cast>().plus(directors).plus(casts)
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastsAdapter.CastViewHolder {
        context = parent.context
        return CastViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.detail_cast,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return directors.size + casts.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class CastViewHolder(val databinding: DetailCastBinding) :
        RecyclerView.ViewHolder(databinding.root) {
        fun bind(position: Int) {
            databinding.cast = allCasts[position]

            val castRoleView = itemView.findViewById<TextView>(R.id.castRole)
            castRoleView.text =
                if (position < directors.size) context.getString(R.string.director)
                else context.getString(R.string.cast)
        }
    }
}