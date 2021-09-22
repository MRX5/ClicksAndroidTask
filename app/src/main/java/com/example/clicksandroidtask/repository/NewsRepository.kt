package com.example.clicksandroidtask.repository

import com.example.clicksandroidtask.network.ApiService
import javax.inject.Inject


private const val COUNTRY="eg"
private const val API_KEY="63b1f94dad044add871d1e319c630265"

class NewsRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getNews() =apiService.getNews(COUNTRY, API_KEY)
}