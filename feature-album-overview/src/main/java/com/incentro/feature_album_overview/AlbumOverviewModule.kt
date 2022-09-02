package com.incentro.feature_album_overview

import android.content.Context
import com.incentro.core_db.AlbumDatabase
import com.incentro.core_db.dao.AlbumDao
import com.incentro.feature_album_overview.data.service.AlbumsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AlbumOverviewModule {

    @Singleton
    @Provides
    fun provideAlbumDetailsService(retrofit: Retrofit): AlbumsService =
        retrofit.create(AlbumsService::class.java)

    @Provides
    fun provideAlbumDao(@ApplicationContext appContext: Context) : AlbumDao = AlbumDatabase.Companion.getDatabase(appContext).albumDao()
}