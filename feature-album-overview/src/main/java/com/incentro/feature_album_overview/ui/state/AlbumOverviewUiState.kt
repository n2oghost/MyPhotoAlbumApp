package com.incentro.feature_album_overview.ui.state

import com.incentro.feature_album_overview.data.model.Album

data class AlbumOverviewUiState(
    val albums: List<Album> = listOf(),
    val loadingState: AlbumOverviewUiLoadingState = AlbumOverviewUiLoadingState.Loading
)

sealed class AlbumOverviewUiLoadingState {
    object Loading : AlbumOverviewUiLoadingState()
    object Success : AlbumOverviewUiLoadingState()
    data class Error(val errorMessage: String?) : AlbumOverviewUiLoadingState()
}