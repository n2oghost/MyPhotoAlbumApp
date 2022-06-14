package com.incentro.feature_album_detail.data

import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumDetailsService {
    @GET("photos")
    suspend fun getUser(@Query("albumId") id: Int): AlbumDataModel
}