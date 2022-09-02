package com.incentro.feature_album_overview.data.model.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumNetworkModel(
    val userId: Int,
    val id: Int,
    val title: String
)