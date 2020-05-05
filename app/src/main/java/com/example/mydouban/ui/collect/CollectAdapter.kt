package com.example.mydouban.ui.collect

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.FragmentCollectItemBinding
import com.example.mydouban.model.Collect

class CollectAdapter :
    RecyclerView.Adapter<CollectAdapter.ViewHolder>() {

    private var collects: MutableList<Collect> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.fragment_collect_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = collects.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collects[position])
    }

    fun updateData(newData: List<Collect>) {
        Log.i("UpdateCollect", newData.toString())
        this.collects.clear()
        this.collects.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val dataBinding: FragmentCollectItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(collect: Collect) {
            Log.i("Collect-View-Bind", collect.toString())
            dataBinding.collect = collect
        }
    }

}

