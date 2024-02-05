package com.incentro.feature_album_detail.ui.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.incentro.core_ui.composable.LoadingScreen
import com.incentro.feature_album_detail.data.model.Photo
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiLoadingState
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel
import org.koin.androidx.compose.koinViewModel

const val PHOTO_LIST_TEST_TAG = "photo_list_test_tag"

@Composable
fun AlbumDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumDetailsViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val loadingState by remember {
        derivedStateOf { state.loadingState }
    }
    val photos by remember {
        derivedStateOf { state.photos }
    }

    LoadingScreen(
        isLoading = loadingState == AlbumDetailsUiLoadingState.Loading,
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
                    (loadingState as AlbumDetailsUiLoadingState.Error).errorMessage,
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
            .testTag(PHOTO_LIST_TEST_TAG)
    ) {
        items(photos, key = { it.id }) { photo ->
            AlbumDetailsPhotoItem(
                item = photo
            )
        }
    }
}