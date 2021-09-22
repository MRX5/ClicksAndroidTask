package com.example.clicksandroidtask.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NavUtils
import com.bumptech.glide.Glide
import com.example.clicksandroidtask.R
import com.example.clicksandroidtask.utils.Constants
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        setupToolbar()
        setupViews()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.icon_arrow_back)
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupViews() {
        val intent = intent
        Glide.with(this).load(intent.getStringExtra(Constants.NEWS_IMAGE_URL))
            .placeholder(R.drawable.placeholder)
            .into(details_news_image)
        details_news_source_name.text = intent.getStringExtra(Constants.NEWS_SOURCE_NAME)
        details_news_title.text = intent.getStringExtra(Constants.NEWS_TITLE)
        details_news_description.text = intent.getStringExtra(Constants.NEWS_DESCRIPTION)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

}