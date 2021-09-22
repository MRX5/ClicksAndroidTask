package com.example.clicksandroidtask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clicksandroidtask.R
import com.example.clicksandroidtask.model.News
import com.example.clicksandroidtask.utils.utils
import kotlinx.android.synthetic.main.news_card.view.*

class NewsAdapter(private var news: List<News>, private val listener: onNewsClickListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var newsCopy = mutableListOf<News>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_card, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position], listener)
    }

    override fun getItemCount() = news.size

    fun addNews(_News: List<News>) {
        news = _News
        newsCopy = _News as MutableList<News>
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        news = if (query.isEmpty()) newsCopy else utils.filter(news, query)
        notifyDataSetChanged()
    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News, listener: onNewsClickListener) {
            itemView.details_news_source_name.text = news.source?.name
            itemView.details_news_title.text = news.title
            itemView.news_time.text = news.publishedAt?.let { utils.getTime(it) }
            Glide.with(itemView.context)
                .load(news.urlToImage)
                .placeholder(R.drawable.placeholder)
                .into(itemView.news_image)
            itemView.setOnClickListener {
                listener.onClick(news)
            }
        }
    }

}