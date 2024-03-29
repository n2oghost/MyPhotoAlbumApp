package com.incentro.feature_album_overview.data.repository

import com.incentro.core_db.dao.AlbumDao
import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.data.model.mapper.asAlbum
import com.incentro.feature_album_overview.data.model.mapper.asDatabaseModel
import com.incentro.feature_album_overview.data.service.AlbumOverviewService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AlbumOverviewRepository(
    private val albumOverviewService: AlbumOverviewService,
    private val albumDao: AlbumDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    val albums: Flow<List<Album>> = albumDao.getAlbums().map {
        it.map { albumDbModel -> albumDbModel.asAlbum() }
    }

    suspend fun fetchLatestAlbums() = withContext(dispatcher) {
        val albums = albumOverviewService.getAlbums()
        albumDao.insertAllAlbums(albums.map { it.asDatabaseModel() })
    }
}