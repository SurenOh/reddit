package com.example.redditandroid.remote.service

import com.example.redditandroid.remote.RedditResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TopService {

    @GET("/top.json")
    suspend fun firstPage(): RedditResponse?

    @GET("/top.json")
    suspend fun nextPage(@Query("after") after: String): RedditResponse?
}