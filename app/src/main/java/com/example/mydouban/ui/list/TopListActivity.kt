package com.example.mydouban.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydouban.R
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.repository.local.dao.CollectDaoOperation
import com.example.mydouban.ui.detail.DetailActivity
import com.example.mydouban.viewModel.TopListViewModel
import kotlinx.android.synthetic.main.activity_top_list.*
import kotlinx.android.synthetic.main.top_list_recycle_scrolling.*

class TopListActivity : AppCompatActivity() {

    private lateinit var topListViewModel: TopListViewModel
    private val adapter by lazy { TopListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_list)
        title = ""
        go_back_to_dashboard.setOnClickListener {
            finish()
        }
        getMovieTop250()
        setRecycle()
    }

    private fun setRecycle() {
        top_250_recycle.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(movieSubject: MovieSubject) {
                Intent(this@TopListActivity, DetailActivity::class.java).also {
                    it.putExtra("id", movieSubject.id)
                    this@TopListActivity.startActivity(it)
                }
            }

            override fun onItemClickWantWatch(movieSubject: MovieSubject) {
                CollectDaoOperation.getInstance()
                    .insertData(this@TopListActivity, movieSubject.toCollect())
                Toast.makeText(
                    this@TopListActivity, "收藏\"${movieSubject.title}\"成功",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        top_250_recycle.adapter = adapter
        top_250_recycle.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        onScrollChangeListener()

    }

    private fun onScrollChangeListener() {
        top_250_recycle.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState);
                val linearManager = (recyclerView.layoutManager as LinearLayoutManager)
                val lastItemPosition = linearManager.findLastVisibleItemPosition()
                val firstItemPosition = linearManager.findFirstVisibleItemPosition()
                linearManager.itemCount
                if (firstItemPosition >= 1) {
                    top_list_toolbar_content.visibility = View.VISIBLE
                }
                if (firstItemPosition == 0) {
                    top_list_toolbar_content.visibility = View.GONE
                }
                if (!topListViewModel.isLoading  && linearManager.itemCount - lastItemPosition == 1 && newState == 0) {
                    println("get onScrollStateChanged ++++++++++++++++++    $lastItemPosition , $firstItemPosition  ${linearManager.itemCount}  $newState")
                    topListViewModel.loadMore()
                }
            }
        }
        )
    }

    private fun getMovieTop250() {
        topListViewModel =
            ViewModelProvider(this).get(TopListViewModel::class.java)
        topListViewModel.movieSubjectsTop250.observe(this, Observer { movies ->
            adapter.updateData(movies)
        })
        topListViewModel.getMovieTop250()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list_top250, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
