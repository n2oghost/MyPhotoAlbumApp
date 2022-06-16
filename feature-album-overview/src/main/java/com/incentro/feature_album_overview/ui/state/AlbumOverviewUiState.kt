package com.incentro.feature_album_overview.ui.state

import com.incentro.feature_album_overview.ui.model.AlbumUiModel

sealed class AlbumOverviewUiState {
    object Loading : AlbumOverviewUiState()
    data class Success(val albums: List<AlbumUiModel>) : AlbumOverviewUiState()
    object Empty : AlbumOverviewUiState()
    data class Error(val errorMessage: String?) : AlbumOverviewUiState()
}