package com.example.redditandroid.di

import com.example.redditandroid.App
import com.example.redditandroid.mapper.TopMapper
import com.example.redditandroid.remote.service.TopService
import com.example.redditandroid.repositoiry.TopRepository
import com.example.redditandroid.repositoiry.TopRepositoryImpl
import com.example.redditandroid.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
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

    //Mapper
    single { TopMapper() }

    //Repository
    single<TopRepository> { TopRepositoryImpl(get(), get()) }

    //ViewModel
    viewModel { HomeViewModel(get()) }
}

fun provideTopService(retrofit: Retrofit): TopService = retrofit.create(TopService::class.java)