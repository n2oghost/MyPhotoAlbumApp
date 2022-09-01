package com.incentro.feature_album_overview.ui.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.incentro.core_ui.composable.LoadingScreen
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiLoadingState
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiState

@Composable
fun AlbumOverviewScreen(
    viewState: AlbumOverviewUiState,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val isLoading = viewState.loadingState == AlbumOverviewUiLoadingState.Loading
    val albums = viewState.albums

    Column(modifier = modifier) {
        LoadingScreen(isLoading = isLoading) {
            LazyColumn {
                items(albums.size) { index ->
                    AlbumOverviewItem(item = albums[index], navController = navController)
                }
            }
        }
    }

    when(viewState.loadingState) {
        is AlbumOverviewUiLoadingState.Error -> {
            Toast.makeText(
                LocalContext.current,
                viewState.loadingState.errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
        else -> {}
    }
}