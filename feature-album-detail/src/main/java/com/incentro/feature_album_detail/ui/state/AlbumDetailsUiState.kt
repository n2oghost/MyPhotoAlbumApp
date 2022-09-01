package com.incentro.feature_album_detail.ui.state

import com.incentro.feature_album_detail.data.model.Photo

sealed class AlbumDetailsUiState {
    object Loading : AlbumDetailsUiState()
    data class Success(val photos: List<Photo>) : AlbumDetailsUiState()
    object Empty : AlbumDetailsUiState()
    data class Error(val errorMessage: String?) : AlbumDetailsUiState()
}