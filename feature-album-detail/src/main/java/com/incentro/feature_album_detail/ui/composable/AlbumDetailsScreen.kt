package com.incentro.feature_album_detail.ui.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.incentro.core_ui.composable.LoadingIndicator
import com.incentro.feature_album_detail.R
import com.incentro.feature_album_detail.data.model.Photo
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel
import org.koin.androidx.compose.koinViewModel

const val PHOTO_LIST_TEST_TAG = "photo_list_test_tag"

@Composable
fun AlbumDetailsScreen(
    viewModel: AlbumDetailsViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val photos by remember {
        derivedStateOf { state.photos }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.album_details_title),
                        style = MaterialTheme.typography.h1
                    )
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
        ) {
            if (state.loading) {
                LoadingIndicator()
            }
            Divider()
            AlbumDetailsPhotoList(
                photos = photos
            )
        }
    }

    state.userMessage?.let {
        Toast.makeText(
            LocalContext.current,
            it,
            Toast.LENGTH_SHORT
        ).show()
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