package com.incentro.feature_album_detail.ui.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.incentro.core_ui.composable.LoadingScreen
import com.incentro.core_ui.composable.TopAppBar
import com.incentro.feature_album_detail.R
import com.incentro.feature_album_detail.ui.model.PhotoUiModel
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiState
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel

const val EMPTY_LIST_MESSAGE = "No photos found."

@Composable
fun AlbumDetailsScreen(
    viewModel: AlbumDetailsViewModel
) {
    val viewState = viewModel.viewStateLiveData.observeAsState()
    var photos by remember {
        mutableStateOf<List<PhotoUiModel>>(listOf())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    Column {
        TopAppBar(titleResource = R.string.album_details_title)
        LoadingScreen(isLoading = isLoading) {
            LazyColumn {
                items(photos.size) { index ->
                    AlbumDetailsPhotoItem(item = photos[index])
                }
            }
        }
    }

    when(viewState.value) {
        AlbumDetailsUiState.Empty -> {
            isLoading = false
            Toast.makeText(
                LocalContext.current,
                EMPTY_LIST_MESSAGE,
                Toast.LENGTH_SHORT
            ).show()
        }
        is AlbumDetailsUiState.Error -> {
            isLoading = false
            Toast.makeText(
                LocalContext.current,
                (viewState.value as AlbumDetailsUiState.Error).errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
        AlbumDetailsUiState.Loading -> {
            isLoading = true
        }
        is AlbumDetailsUiState.Success -> {
            isLoading = false
            photos = (viewState.value as AlbumDetailsUiState.Success).photos
        }
        else -> { isLoading = false }
    }
}