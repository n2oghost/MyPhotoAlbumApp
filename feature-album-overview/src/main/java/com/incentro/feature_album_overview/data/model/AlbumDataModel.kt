package com.incentro.feature_album_overview.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumDataModel(
    val userId: Int,
    val id: Int,
    val title: String
)