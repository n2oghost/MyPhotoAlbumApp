package com.incentro.feature_album_overview.ui.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.incentro.core_ui.composable.LoadingScreen
import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiLoadingState

@Composable
fun AlbumOverviewScreen(
    albums: List<Album>,
    loadingState: AlbumOverviewUiLoadingState,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val isLoading = loadingState == AlbumOverviewUiLoadingState.Loading

    LoadingScreen(
        isLoading = isLoading,
        modifier = modifier
    ) {
        AlbumOverviewList(
            albums = albums,
            navController = navController
        )
    }

    when(loadingState) {
        is AlbumOverviewUiLoadingState.Error -> {
            val context = LocalContext.current
            LaunchedEffect(true) {
                Toast.makeText(
                    context,
                    loadingState.errorMessage,
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
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        modifier = modifier
    ) {
        items(albums.size) { index ->
            AlbumOverviewItem(
                item = albums[index],
                navController = navController,
            )
        }
    }
}