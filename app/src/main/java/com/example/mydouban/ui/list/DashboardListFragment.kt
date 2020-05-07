package com.example.mydouban.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydouban.R
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.repository.local.dao.CollectDaoOperation
import com.example.mydouban.ui.detail.DetailActivity
import com.gturedi.views.StatefulLayout
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardListFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private val topAdapter by lazy { DashboardTopAdapter() }
    private val inTheaterAdapter by lazy { DashboardInTheaterAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val  dashboardStateView = root.findViewById<StatefulLayout>(R.id.dashboard_state_view)
        dashboardStateView.showLoading(R.string.is_loading)
        getMovieList()
        return root
    }

    private fun getMovieList() {
        dashboardViewModel.movieSubjectsTop6.observe(viewLifecycleOwner, Observer { movies ->
            dashboard_state_view.showContent()
            topAdapter.updateData(movies)
        })

        dashboardViewModel.movieInTheater.observe(viewLifecycleOwner, Observer { movies ->
            inTheaterAdapter.updateData(movies)
        })
        dashboardViewModel.getMovieTop6()
        dashboardViewModel.getMovieInTheater()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycle()
        btn_to_top_list.setOnClickListener {
            startActivity(Intent(this.requireContext(), TopListActivity::class.java))
        }
        btn_to_movie_list.setOnClickListener {
            startActivity(Intent(this.requireContext(), TopListActivity::class.java))
        }
    }

    private fun initRecycle() {
        dashboard_top_recycle.layoutManager = GridLayoutManager(this.context, 3)
        topAdapter.setOnItemClickListener(onItemClickListener)
        dashboard_top_recycle.adapter = topAdapter

        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        dashboard_in_theater_recycle.layoutManager = linearLayoutManager
        inTheaterAdapter.setOnItemClickListener(onItemClickListener)
        dashboard_in_theater_recycle.adapter = inTheaterAdapter
    }

    private val onItemClickListener: OnItemClickListener = object : OnItemClickListener {
        override fun onItemClick(movieSubject: MovieSubject) {
            Intent(this@DashboardListFragment.requireContext(), DetailActivity::class.java).also {
                it.putExtra("id", movieSubject.id)
                this@DashboardListFragment.startActivity(it)
            }
        }
    }

}
