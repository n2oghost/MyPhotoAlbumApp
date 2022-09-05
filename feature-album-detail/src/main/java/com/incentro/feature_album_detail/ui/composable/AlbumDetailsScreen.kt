package com.incentro.feature_album_detail.ui.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
        AlbumDetailsPhotoList(
            photos = photos
        )
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

@Composable
fun AlbumDetailsPhotoList(
    photos: List<Photo>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        modifier = modifier
    ) {
        items(photos.size) { index ->
            AlbumDetailsPhotoItem(
                item = photos[index]
            )
        }
    }
}