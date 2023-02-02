package com.example.redditandroid.repositoiry

import com.example.redditandroid.model.TopRedditModel

interface TopRepository {
    suspend fun getFirstPage(): ArrayList<TopRedditModel>

    suspend fun nextPage(): ArrayList<TopRedditModel>
}