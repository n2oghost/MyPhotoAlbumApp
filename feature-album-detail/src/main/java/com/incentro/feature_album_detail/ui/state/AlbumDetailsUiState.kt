package com.incentro.feature_album_detail.ui.state

import com.incentro.feature_album_detail.data.model.Photo

data class AlbumDetailsUiState(
    val photos: List<Photo> = listOf(),
    val loading: Boolean = false,
    val userMessage: Int? = null
)