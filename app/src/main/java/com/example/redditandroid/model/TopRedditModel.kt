package com.example.redditandroid.model

data class TopRedditModel(
    val author: String?,
    val time: Double?,
    val title: String?,
    val thumbnail: String?,
    val commentCount: String?,
    val image: String?,
    val isVideo: Boolean?,
    val name: String?,
    val thumbnailHeight: Int?,
    val thumbnailWidth: Int?
)