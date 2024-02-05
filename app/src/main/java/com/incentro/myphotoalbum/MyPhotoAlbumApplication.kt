package com.incentro.myphotoalbum

import android.app.Application
import com.incentro.feature_album_detail.albumDetailsModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class MyPhotoAlbumApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyPhotoAlbumApplication)
            modules(albumDetailsModule)
        }
    }
}