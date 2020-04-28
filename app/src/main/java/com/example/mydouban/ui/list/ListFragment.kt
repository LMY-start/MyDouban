package com.example.mydouban.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydouban.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private val adapter by lazy { MovieTopAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        listViewModel.text.observe(viewLifecycleOwner, Observer {
            text_home.text = it
        })

        listViewModel.movieSubjects.observe(viewLifecycleOwner, Observer { movies ->
            adapter.updateData(movies)
        })

        listViewModel.getMovieTop()
        return root
    }

    override fun onStart() {
        super.onStart()
        movie_top_list.layoutManager = LinearLayoutManager(this.context)
        movie_top_list.adapter = MovieTopAdapter()
    }
}
