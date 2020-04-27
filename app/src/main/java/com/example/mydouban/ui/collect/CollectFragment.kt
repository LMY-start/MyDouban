package com.example.mydouban.ui.collect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mydouban.R
import kotlinx.android.synthetic.main.fragment_collect.*

class CollectFragment : Fragment() {

    private lateinit var collectViewModel: CollectViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        collectViewModel =
                ViewModelProvider(this).get(CollectViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_collect, container, false)
        collectViewModel.text.observe(viewLifecycleOwner, Observer {
            text_collect.text = it
        })
        return root
    }
}
