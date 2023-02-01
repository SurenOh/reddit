package com.example.redditandroid.remote.service

import com.example.redditandroid.remote.RedditResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TopService {

    @GET("/top.json")
    fun getTopReddits(): RedditResponse?

    @GET("/top.json")
    fun nextPage(@Query("after") after: String): RedditResponse?
}