package com.incentro.feature_album_detail

import com.incentro.feature_album_detail.data.AlbumDetailsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlbumDetailsModule {

    @Singleton
    @Provides
    fun provideAlbumDetailsService(retrofit: Retrofit): AlbumDetailsService =
        retrofit.create(AlbumDetailsService::class.java)
}