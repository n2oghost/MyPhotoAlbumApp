package com.incentro.feature_album_overview.data.service

import com.incentro.feature_album_overview.data.model.network.AlbumNetworkModel
import retrofit2.http.GET

interface AlbumsService {
    @GET("albums")
    suspend fun getAlbums(): List<AlbumNetworkModel>
}