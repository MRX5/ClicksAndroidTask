package com.example.clicksandroidtask.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clicksandroidtask.R
import com.example.clicksandroidtask.model.News
import com.example.clicksandroidtask.ui.adapter.NewsAdapter
import com.example.clicksandroidtask.ui.adapter.onNewsClickListener
import com.example.clicksandroidtask.ui.viewmodel.NewsViewModel
import com.example.clicksandroidtask.utils.Constants
import com.example.clicksandroidtask.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news.*

@AndroidEntryPoint
class NewsActivity : AppCompatActivity(), onNewsClickListener {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setupSearchView()
        setupRecyclerView()
        loadNews()
    }

    private fun setupSearchView() {
        news_search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { adapter.filter(newText) }
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter(emptyList(), this)
        news_recycler_view.layoutManager = LinearLayoutManager(this)
        news_recycler_view.adapter = adapter
    }

    private fun loadNews() {
        viewModel.loadNews().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    news_progress_bar.visibility = VISIBLE
                }
                Status.SUCCESS -> {
                    news_progress_bar.visibility = GONE
                    it.data?.let { data ->
                        adapter.addNews(data.articles)
                    }
                }
                Status.ERROR -> {
                    news_progress_bar.visibility = GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onClick(news: News) {
        val intent = Intent(this, NewsDetailsActivity::class.java).apply {
            putExtra(Constants.NEWS_IMAGE_URL, news.urlToImage)
            putExtra(Constants.NEWS_SOURCE_NAME, news.source?.name)
            putExtra(Constants.NEWS_TITLE, news.title)
            putExtra(Constants.NEWS_DESCRIPTION, news.description)
        }
        startActivity(intent)
    }

}