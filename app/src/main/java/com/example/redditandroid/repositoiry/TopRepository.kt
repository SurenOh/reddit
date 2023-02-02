package com.example.redditandroid.repositoiry

import com.example.redditandroid.model.TopRedditModel

interface TopRepository {
    suspend fun firstPage(): ArrayList<TopRedditModel>

    suspend fun nextPage(after: String?): ArrayList<TopRedditModel>
}