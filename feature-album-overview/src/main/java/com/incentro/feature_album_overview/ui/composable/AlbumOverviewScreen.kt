package com.incentro.feature_album_overview.ui.composable

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
import androidx.navigation.NavController
import com.incentro.core_ui.composable.LoadingScreen
import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiState
import com.incentro.feature_album_overview.ui.viewmodel.AlbumOverviewViewModel

const val EMPTY_LIST_MESSAGE = "No albums found."

@Composable
fun AlbumOverviewScreen(
    viewModel: AlbumOverviewViewModel,
    navController: NavController
) {
    val viewState = viewModel.viewStateLiveData.observeAsState()
    var albums by remember {
        mutableStateOf<List<Album>>(listOf())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    Column {
        LoadingScreen(isLoading = isLoading) {
            LazyColumn {
                items(albums.size) { index ->
                    AlbumOverviewItem(item = albums[index], navController = navController)
                }
            }
        }
    }

    when(viewState.value) {
        AlbumOverviewUiState.Empty -> {
            isLoading = false
            Toast.makeText(
                LocalContext.current,
                EMPTY_LIST_MESSAGE,
                Toast.LENGTH_SHORT
            ).show()
        }
        is AlbumOverviewUiState.Error -> {
            isLoading = false
            Toast.makeText(
                LocalContext.current,
                (viewState.value as AlbumOverviewUiState.Error).errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
        AlbumOverviewUiState.Loading -> {
            isLoading = true
        }
        is AlbumOverviewUiState.Success -> {
            isLoading = false
            albums = (viewState.value as AlbumOverviewUiState.Success).albums
        }
        else -> { isLoading = false }
    }
}