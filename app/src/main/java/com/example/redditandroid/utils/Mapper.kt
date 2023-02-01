package com.example.redditandroid.utils

interface Mapper<Dto, Model> {

    fun mapDtoToModel(dto: Dto?): Model

    fun mapListDtoToModel(dto: ArrayList<Dto>?) = ArrayList( dto?.map { mapDtoToModel(it) } )
}