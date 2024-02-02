package com.incentro.feature_album_detail.ui.state

import com.incentro.feature_album_detail.data.model.Photo

data class AlbumDetailsUiState(
    val photos: List<Photo> = listOf(),
    val loadingState: AlbumDetailsUiLoadingState = AlbumDetailsUiLoadingState.Loading
)

sealed class AlbumDetailsUiLoadingState {
    data object Loading : AlbumDetailsUiLoadingState()
    data object Success : AlbumDetailsUiLoadingState()
    data class Error(val errorMessage: String?) : AlbumDetailsUiLoadingState()
}