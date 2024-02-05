package com.incentro.myphotoalbum

import android.app.Application
import com.incentro.feature_album_detail.albumDetailsModule
import com.incentro.feature_album_overview.albumOverviewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyPhotoAlbumApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyPhotoAlbumApplication)
            modules(
                albumDetailsModule,
                albumOverviewModule
            )
        }
    }
}