package com.example.clicksandroidtask.model

import com.google.gson.annotations.SerializedName

data class Articles(
    @SerializedName("articles")
    val articles: List<News>
)
