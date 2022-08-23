package com.incentro.feature_album_overview.data.repository

import com.incentro.core_db.dao.AlbumDao
import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.data.model.mapper.asAlbum
import com.incentro.feature_album_overview.data.model.mapper.asDatabaseModel
import com.incentro.feature_album_overview.data.service.AlbumsService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AlbumsRepository @Inject constructor(
    private val albumsService: AlbumsService,
    private val albumDao: AlbumDao
) {

    val albums: Flow<List<Album>> = albumDao.getAlbums().map {
        it.map { albumDbModel -> albumDbModel.asAlbum() }
    }

    suspend fun fetchAlbums() {
        withContext(Dispatchers.IO) {
            val albums = albumsService.getAlbums()
            albumDao.insertAllAlbums(albums.map { it.asDatabaseModel() })
        }
    }
}