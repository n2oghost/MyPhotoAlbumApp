package com.incentro.feature_album_detail.data.repository

import com.incentro.core_db.dao.AlbumDao
import com.incentro.feature_album_detail.data.model.Photo
import com.incentro.feature_album_detail.data.model.asDatabaseModel
import com.incentro.feature_album_detail.data.model.asPhoto
import com.incentro.feature_album_detail.data.service.AlbumDetailsService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AlbumDetailsRepository @Inject constructor(
    private val albumDetailsService: AlbumDetailsService,
    private val albumDao: AlbumDao
) {

    var album: Flow<List<Photo>> = flow {}

    suspend fun fetchAlbumById(id: Int) {
        album = albumDao.getPhotos(id).map {
            it.map { albumDbModel -> albumDbModel.asPhoto() }
        }
        withContext(Dispatchers.IO) {
            val album = albumDetailsService.getAlbumById(id)
            albumDao.insertAllPhotos(album.map { it.asDatabaseModel() })
        }
    }
}