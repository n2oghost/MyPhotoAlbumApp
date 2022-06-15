package com.incentro.feature_album_detail.ui.state

import com.incentro.feature_album_detail.ui.model.PhotoUiModel

sealed class AlbumDetailsUiState {
    object Loading : AlbumDetailsUiState()
    data class Success(val photos: List<PhotoUiModel>) : AlbumDetailsUiState()
    object Empty : AlbumDetailsUiState()
    data class Error(val errorMessage: String?) : AlbumDetailsUiState()
}