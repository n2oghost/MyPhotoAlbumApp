package com.incentro.feature_album_overview

import com.incentro.feature_album_overview.data.service.AlbumsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlbumOverviewModule {

    @Singleton
    @Provides
    fun provideAlbumDetailsService(retrofit: Retrofit): AlbumsService =
        retrofit.create(AlbumsService::class.java)
}