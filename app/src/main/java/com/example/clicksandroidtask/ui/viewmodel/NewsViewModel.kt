package com.example.clicksandroidtask.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clicksandroidtask.model.Articles
import com.example.clicksandroidtask.repository.NewsRepository
import com.example.clicksandroidtask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val newsLiveData = MutableLiveData<Resource<Articles>>()

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            newsLiveData.value = Resource.loading(null)
            try {
                repository.getNews().let {
                    if (it.isSuccessful) {
                        newsLiveData.value = Resource.success(it.body())
                    } else {
                        newsLiveData.value = Resource.error(it.message())
                    }
                }
            } catch (e: Exception) {
                newsLiveData.value = Resource.error(e.message.toString())
            }
        }
    }

    fun loadNews() = newsLiveData
}