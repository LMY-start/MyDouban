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
        dashboardViewModel.movieSubjectsTop6.observe(viewLifecycleOwner, Observer { movies ->
            topAdapter.updateData(movies)
        })

        dashboardViewModel.movieInTheater.observe(viewLifecycleOwner, Observer { movies ->
            inTheaterAdapter.updateData(movies)
        })
        dashboardViewModel.getMovieTop6()
        dashboardViewModel.getMovieInTheater()

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dashboard_top_recycle.layoutManager = GridLayoutManager(this.context, 3)
        dashboard_top_recycle.adapter = topAdapter
        dashboard_top_recycle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }

        }
        )
        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        dashboard_in_theater_recycle.layoutManager = linearLayoutManager
        dashboard_in_theater_recycle.adapter = inTheaterAdapter

        btn_to_top_list.setOnClickListener {
            startActivity(Intent(this.requireContext(), TopListActivity::class.java))
        }
        btn_to_movie_list.setOnClickListener {
            Toast.makeText(this.context, "startActivity to 500 movies", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this.requireContext(),TopListActivity::class.java))
        }
    }
}
