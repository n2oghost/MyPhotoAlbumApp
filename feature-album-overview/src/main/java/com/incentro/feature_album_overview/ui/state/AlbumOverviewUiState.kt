package com.incentro.feature_album_overview.ui.state

import com.incentro.feature_album_overview.data.model.Album

data class AlbumOverviewUiState(
    val albums: List<Album> = listOf(),
    val loading: Boolean = false,
    val userMessage: Int? = null
)