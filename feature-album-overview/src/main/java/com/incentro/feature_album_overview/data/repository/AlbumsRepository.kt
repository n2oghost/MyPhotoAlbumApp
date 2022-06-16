package com.incentro.feature_album_overview.data.repository

import com.incentro.feature_album_overview.data.model.AlbumDataModel
import com.incentro.feature_album_overview.data.service.AlbumsService
import javax.inject.Inject

class AlbumsRepository @Inject constructor(
    private val albumsService: AlbumsService
) {
    suspend fun getAlbums() : List<AlbumDataModel> {
        return albumsService.getAlbums()
    }
}