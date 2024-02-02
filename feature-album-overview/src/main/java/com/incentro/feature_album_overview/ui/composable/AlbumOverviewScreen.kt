package com.incentro.feature_album_overview.ui.composable

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.incentro.core_ui.composable.LoadingScreen
import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiLoadingState
import com.incentro.feature_album_overview.ui.viewmodel.AlbumOverviewViewModel

const val ALBUM_LIST_TEST_TAG = "album_list_test_tag"

@Composable
fun AlbumOverviewScreen(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AlbumOverviewViewModel = hiltViewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val albums by remember {
        derivedStateOf { state.albums }
    }
    val loadingState by remember {
        derivedStateOf { state.loadingState }
    }

    LoadingScreen(
        isLoading = loadingState == AlbumOverviewUiLoadingState.Loading,
        modifier = modifier
    ) {
        AlbumOverviewList(
            albums = albums,
            navigateTo = navigateTo
        )
    }

    when(loadingState) {
        is AlbumOverviewUiLoadingState.Error -> {
            val context = LocalContext.current
            LaunchedEffect(true) {
                Toast.makeText(
                    context,
                    (loadingState as AlbumOverviewUiLoadingState.Error).errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        else -> {}
    }
}

@Composable
fun AlbumOverviewList(
    albums: List<Album>,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        modifier = modifier
            .testTag(ALBUM_LIST_TEST_TAG)
    ) {
        items(albums, key = { it.id }) { album ->
            AlbumOverviewItem(
                item = album,
                navigateTo = navigateTo,
            )
        }
    }
}