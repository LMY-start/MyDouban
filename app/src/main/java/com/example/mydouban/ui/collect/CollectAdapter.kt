package com.example.mydouban.ui.collect

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.databinding.FragmentCollectItemBinding
import com.example.mydouban.model.Collect
import com.example.mydouban.repository.local.dao.CollectDaoOperation
import com.skydoves.powermenu.MenuAnimation
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem

class CollectAdapter : RecyclerView.Adapter<CollectAdapter.ViewHolder>() {

    private var collects: MutableList<Collect> = mutableListOf()
    private var onItemClickListener: OnCollectItemClickListener? = null
    private val collectListener by lazy { CollectItemEventListener() }

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
        val operateBtn = holder.itemView.findViewById<ImageView>(R.id.operate_btn)

        val itemMenu = getItemMenu(holder.itemView.context, position)
        operateBtn.setOnClickListener {
            itemMenu.showAsAnchorLeftBottom(it)
        }

        holder.bind(collects[position])
    }

    private fun getItemMenu(context: Context, position: Int): PowerMenu {
        val powerMenu = PowerMenu.Builder(context)
            .addItem(PowerMenuItem("删除"))
            .setAnimation(MenuAnimation.DROP_DOWN)
            .setMenuRadius(10f)
            .setMenuShadow(10f)
            .setTextColor(ContextCompat.getColor(context, R.color.black))
            .setTextTypeface(Typeface.DEFAULT)
            .setShowBackground(false)
            .build()

        powerMenu.setOnMenuItemClickListener { _, _ ->
            powerMenu.dismiss()
            deleteSelectedItem(context, position)
        }
        return powerMenu
    }

    private fun deleteSelectedItem(context: Context, position: Int) {
        val id = collects[position].id
        CollectDaoOperation.getInstance().deleteByKeyData(context, id)
        onItemClickListener?.let { it.onDeleteMenuClick() }
    }

    fun updateData(newData: List<Collect>) {
        this.collects.clear()
        this.collects.addAll(newData)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnCollectItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    inner class ViewHolder(val dataBinding: FragmentCollectItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(collect: Collect) {
            Log.i("Collect-View-Bind", collect.toString())
            dataBinding.collect = collect
            dataBinding.listener = collectListener
        }
    }

}

