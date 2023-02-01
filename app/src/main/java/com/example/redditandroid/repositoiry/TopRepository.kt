package com.example.redditandroid.repositoiry

import com.example.redditandroid.model.TopRedditModel

interface TopRepository {
    fun getFirstPage(): ArrayList<TopRedditModel>

    fun nextPage(): ArrayList<TopRedditModel>
}