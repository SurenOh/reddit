package com.example.redditandroid.remote.model

import com.google.gson.annotations.SerializedName

data class RedditDetailDto(
    val thumbnail: String,
    val title: String,
    val created: Double,
    @SerializedName("num_comments")
    val numComments: Long,
    val author: String,
    @SerializedName("url_overridden_by_dest")
    val image: String,
    @SerializedName("is_video")
    val isVideo: Boolean,
    val name: String,
    @SerializedName("thumbnail_height")
    val thumbnailHeight: Int,
    @SerializedName("thumbnail_width")
    val thumbnailWidth: Int
)