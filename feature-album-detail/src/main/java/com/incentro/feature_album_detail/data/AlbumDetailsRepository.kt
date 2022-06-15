package com.incentro.feature_album_detail.data

import javax.inject.Inject

class AlbumDetailsRepository @Inject constructor(
    private val albumDetailsService: AlbumDetailsService
) {
    suspend fun getAlbumById(id: Int) : AlbumDataModel {
        return albumDetailsService.getUser(id)
    }
}