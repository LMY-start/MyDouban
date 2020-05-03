package com.example.mydouban.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydouban.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_top_list.*
import kotlinx.android.synthetic.main.top_list_recycle_scrolling.*
import kotlin.math.abs

class TopListActivity : AppCompatActivity() {

    private lateinit var topListViewModel: TopListViewModel
    private val adapter by lazy { TopListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_list)
        setSupportActionBar(toolbar)
        title = ""

        go_back_to_dashboard.setOnClickListener {
            finish()
        }
        addAppBarOffsetChangListener()

        getMovieTop250()

        top_250_recycle.layoutManager = LinearLayoutManager(this)
        top_250_recycle.adapter = adapter

    }

    private fun getMovieTop250() {
        topListViewModel =
            ViewModelProvider(this).get(TopListViewModel::class.java)
        topListViewModel.movieSubjectsTop250.observe(this, Observer { movies ->
            adapter.updateData(movies)
        })
        topListViewModel.getMovieTop250(this)
    }

    private fun addAppBarOffsetChangListener() {
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener,
            View.OnClickListener {

            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                when {
                    verticalOffset == 0 -> {
                        println("展开状态")
                        top_list_toolbar_content.visibility = View.GONE
                        list_top250_describe.visibility = View.VISIBLE

                    }
                    abs(verticalOffset) >= appBarLayout?.totalScrollRange!! -> {
                        println("折叠状态");
                        top_list_toolbar_content.visibility = View.VISIBLE
                        list_top250_describe.visibility = View.GONE
                    }
                    else -> {
                        println("中间状态");
                        top_list_toolbar_content.visibility = View.GONE
                        list_top250_describe.visibility = View.VISIBLE

                    }
                }
            }

            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_list_top250, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_share -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
