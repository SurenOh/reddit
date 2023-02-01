package com.example.redditandroid.remote.model

import com.google.gson.annotations.SerializedName

data class DataDto(
    val after: String,
    val dist: Long,
    val modhash: String,
    @SerializedName("geo_filter")
    val geoFilter: String,
    val children: ArrayList<TopRedditDto>,
    val before: String
)