package com.incentro.feature_album_detail.data.repository

import com.incentro.core_db.dao.AlbumDao
import com.incentro.feature_album_detail.data.model.Photo
import com.incentro.feature_album_detail.data.model.asDatabaseModel
import com.incentro.feature_album_detail.data.model.asPhoto
import com.incentro.feature_album_detail.data.service.AlbumDetailsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AlbumDetailsRepository(
    private val albumDetailsService: AlbumDetailsService,
    private val albumDao: AlbumDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getLocalAlbumById(id: Int): Flow<List<Photo>> {
        return albumDao.getPhotos(id).map {
            it.map { albumDbModel -> albumDbModel.asPhoto() }
        }
    }

    suspend fun fetchLatestAlbumById(id: Int) = withContext(dispatcher) {
        val album = albumDetailsService.getAlbumById(id)
        albumDao.insertAllPhotos(album.map { it.asDatabaseModel() })
    }
}