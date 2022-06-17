package com.incentro.myphotoalbum

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.incentro.feature_album_overview.ui.model.AlbumUiModel
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiState
import com.incentro.feature_album_overview.ui.viewmodel.AlbumOverviewViewModel

const val EMPTY_LIST_MESSAGE = "No albums found."

@Composable
fun AlbumOverviewScreen(
    viewModel: AlbumOverviewViewModel,
    navController: NavController
) {
    var albums by remember {
        mutableStateOf<List<AlbumUiModel>>(listOf())
    }
    LazyColumn {
        items(albums.size) { index ->
            AlbumOverviewItem(albums[index], navController = navController)
        }
    }

    val viewState = viewModel.viewStateLiveData.observeAsState()
    when(viewState.value) {
        AlbumOverviewUiState.Empty -> {
            Toast.makeText(
                LocalContext.current,
                EMPTY_LIST_MESSAGE,
                Toast.LENGTH_SHORT
            ).show()
        }
        is AlbumOverviewUiState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (viewState.value as AlbumOverviewUiState.Error).errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
        AlbumOverviewUiState.Loading -> {

        }
        is AlbumOverviewUiState.Success -> {
            albums = (viewState.value as AlbumOverviewUiState.Success).albums
        }
        else -> {}
    }
}