package com.example.mydouban.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydouban.R
import com.example.mydouban.common.ScreenUtils
import com.example.mydouban.model.MovieSubject
import com.example.mydouban.repository.local.dao.CollectDaoOperation
import com.example.mydouban.ui.detail.DetailActivity
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
        setRecycle()
    }

    private fun setRecycle() {
        top_250_recycle.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickListener(object : TopListAdapter.OnItemClickListener {
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
        top_list_nested_scroll.setOnScrollChangeListener { view: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            val topListPosition = top_250_recycle.y + top_250_recycle.height
            val screenHeight = ScreenUtils.getScreenHeight(this)
            if (topListPosition - scrollY <= 1840 && scrollY - oldScrollY > 200) {
                println("topListPosition =  $topListPosition   scrollY = $scrollY,  ${topListPosition - scrollY},  height =  $screenHeight")
                topListViewModel.loadMore(this)
            }
        }
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
