package com.example.mydouban.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.DetailCastBinding
import com.example.mydouban.model.Cast

class CastsAdapter(private var casts: MutableList<Cast>) :
    RecyclerView.Adapter<CastsAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastsAdapter.CastViewHolder {
        return CastViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.detail_cast,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return casts.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(casts[position])
    }

    inner class CastViewHolder(val databinding: DetailCastBinding) :
        RecyclerView.ViewHolder(databinding.root) {
        fun bind(cast: Cast) {
            databinding.cast = cast
        }
    }
}