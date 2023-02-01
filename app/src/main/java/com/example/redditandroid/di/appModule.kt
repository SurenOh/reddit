package com.example.redditandroid.di

import com.example.redditandroid.App
import com.example.redditandroid.remote.service.TopService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = "https://www.reddit.com/"

val applicationModule = module {
    single { androidApplication() as App }

//Retrofit
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

//Service
    factory { provideTopService(get()) }

}

fun provideTopService(retrofit: Retrofit): TopService = retrofit.create(TopService::class.java)