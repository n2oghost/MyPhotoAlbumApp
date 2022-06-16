package com.incentro.feature_album_detail.data.service

import com.incentro.feature_album_detail.data.model.PhotoDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumDetailsService {
    @GET("photos")
    suspend fun getAlbumById(@Query("albumId") id: Int): List<PhotoDataModel>
}