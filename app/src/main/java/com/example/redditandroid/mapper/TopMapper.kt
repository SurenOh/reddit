package com.example.redditandroid.mapper

import com.example.redditandroid.model.TopRedditModel
import com.example.redditandroid.remote.model.TopRedditDto
import com.example.redditandroid.utils.Mapper

class TopMapper : Mapper<TopRedditDto, TopRedditModel> {

    override fun mapDtoToModel(dto: TopRedditDto?) = TopRedditModel(
        author = dto?.data?.author,
        title = dto?.data?.title,
        time = dto?.data?.created,
        commentCount = dto?.data?.numComments.toString(),
        thumbnail = dto?.data?.thumbnail,
        image = dto?.data?.image,
        isVideo = dto?.data?.isVideo,
        name = dto?.data?.name,
        thumbnailWidth = dto?.data?.thumbnailWidth,
        thumbnailHeight = dto?.data?.thumbnailHeight
    )

}