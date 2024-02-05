package com.incentro.core_db

import android.content.Context
import com.incentro.core_db.dao.AlbumDao
import org.koin.dsl.module

val dataBaseModule = module {
    factory { provideAlbumDao(get()) }
}

fun provideAlbumDao(appContext: Context) : AlbumDao = AlbumDatabase.getDatabase(appContext).albumDao()