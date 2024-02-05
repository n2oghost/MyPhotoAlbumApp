package com.incentro.core.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

val networkModule = module {
    single { provideRetrofit() }
    factory { provideDispatcher() }
}

private fun provideRetrofit() : Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

private fun provideDispatcher() : CoroutineDispatcher = Dispatchers.IO