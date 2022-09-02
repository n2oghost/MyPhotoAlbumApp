package com.incentro.feature_album_detail.ui.composable

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.incentro.core_ui.composable.LoadingScreen
import com.incentro.feature_album_detail.data.model.Photo
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiLoadingState

@Composable
fun AlbumDetailsScreen(
    photos: List<Photo>,
    loadingState: AlbumDetailsUiLoadingState,
    modifier: Modifier = Modifier
) {
    val isLoading = loadingState == AlbumDetailsUiLoadingState.Loading

    LoadingScreen(
        isLoading = isLoading,
        modifier = modifier
    ) {
        LazyColumn {
            items(photos.size) { index ->
                AlbumDetailsPhotoItem(item = photos[index])
            }
        }
    }

    when(loadingState) {
        is AlbumDetailsUiLoadingState.Error -> {
            val context = LocalContext.current
            LaunchedEffect(true) {
                Toast.makeText(
                    context,
                    loadingState.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        else -> {  }
    }
}