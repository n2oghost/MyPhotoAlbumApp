package com.incentro.feature_album_detail.data.repository

import com.incentro.feature_album_detail.data.service.AlbumDetailsService
import com.incentro.feature_album_detail.data.model.PhotoDataModel
import javax.inject.Inject

class AlbumDetailsRepository @Inject constructor(
    private val albumDetailsService: AlbumDetailsService
) {
    suspend fun getAlbumById(id: Int) : List<PhotoDataModel> {
        return albumDetailsService.getAlbumById(id)
    }
}