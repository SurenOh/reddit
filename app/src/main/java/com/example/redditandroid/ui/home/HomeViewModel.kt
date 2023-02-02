package com.example.redditandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redditandroid.model.TopRedditModel
import com.example.redditandroid.repositoiry.TopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val topRepository: TopRepository): ViewModel() {
    val reddits = MutableLiveData<ArrayList<TopRedditModel>>()

    fun firstPage() {
        viewModelScope.launch(Dispatchers.IO) {
            reddits.postValue(topRepository.getFirstPage())
        }
    }

}