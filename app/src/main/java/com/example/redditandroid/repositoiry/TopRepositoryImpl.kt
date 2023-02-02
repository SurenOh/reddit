package com.example.redditandroid.repositoiry

import com.example.redditandroid.mapper.TopMapper
import com.example.redditandroid.model.TopRedditModel
import com.example.redditandroid.remote.service.TopService

class TopRepositoryImpl(private val topService: TopService, private val topMapper: TopMapper): TopRepository {

    override suspend fun getFirstPage() = topMapper.mapListDtoToModel(topService.firstPage()?.data?.children)

    override suspend fun nextPage(): ArrayList<TopRedditModel> {
        TODO("Not yet implemented")
    }

}