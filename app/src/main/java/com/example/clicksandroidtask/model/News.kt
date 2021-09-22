package com.example.clicksandroidtask.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("title") val title: String?,
    @SerializedName("source") val source: Source?,
    @SerializedName("description") val description: String?,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: String?
)

data class Source(
    @SerializedName("name") val name: String
)
