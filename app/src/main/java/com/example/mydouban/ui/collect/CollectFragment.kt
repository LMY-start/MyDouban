package com.example.mydouban.ui.collect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mydouban.R
import com.example.mydouban.viewModel.CollectViewModel
import kotlinx.android.synthetic.main.fragment_collect.*

class CollectFragment : Fragment() {

    private lateinit var collectViewModel: CollectViewModel
    private val adapter by lazy { CollectAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        collectViewModel =
            ViewModelProvider(this).get(CollectViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_collect, container, false)

        collectViewModel.collectList.observe(viewLifecycleOwner, Observer { collects ->
            adapter.updateData(collects)
        })
        collectViewModel.getAllCollect()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        collect_list.layoutManager = GridLayoutManager(this.context, 1)
        adapter.setOnItemClickListener(onItemClickListener)
        collect_list.adapter = adapter
        collect_list.addItemDecoration(
            DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        )
    }

    private val onItemClickListener: OnCollectItemClickListener =
        object : OnCollectItemClickListener {

            override fun onDeleteMenuClick() {
                collectViewModel.getAllCollect()
            }
        }
}
