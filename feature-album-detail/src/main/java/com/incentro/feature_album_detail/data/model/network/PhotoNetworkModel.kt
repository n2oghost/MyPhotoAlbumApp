package com.incentro.feature_album_detail.data.model.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoNetworkModel(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)