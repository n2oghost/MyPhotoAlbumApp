package com.incentro.myphotoalbum

import androidx.compose.runtime.Composable
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiState
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel

@Composable
fun AlbumDetailsScreen(
    viewModel: AlbumDetailsViewModel
) {
    viewModel.viewStateLiveData.value?.let {
        when(it) {
            AlbumDetailsUiState.Empty -> TODO()
            is AlbumDetailsUiState.Error -> TODO()
            AlbumDetailsUiState.Loading -> TODO()
            is AlbumDetailsUiState.Success -> TODO()
        }
    }
}