package com.example.mydouban.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mydouban.R
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardListFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private val adapter by lazy { DashboardListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel.movieSubjectsTop6.observe(viewLifecycleOwner, Observer { movies ->
            adapter.updateData(movies)
        })
        dashboardViewModel.getMovieTop6()

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dashboard_recycle.layoutManager = GridLayoutManager(this.context,3)
        dashboard_recycle.adapter = adapter

        btn_to_top_list.setOnClickListener {
            startActivity(Intent(this.requireContext(),TopListActivity::class.java))
        }
    }
}
