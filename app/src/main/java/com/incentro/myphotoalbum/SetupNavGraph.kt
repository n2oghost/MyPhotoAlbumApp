package com.incentro.myphotoalbum

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel
import com.incentro.feature_album_overview.ui.viewmodel.AlbumOverviewViewModel

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "album-overview") {
        composable("album-overview") { backStackEntry ->
            val viewModel = hiltViewModel<AlbumOverviewViewModel>()
            AlbumOverviewScreen(viewModel, navController)
        }
        composable("album-details") { backStackEntry ->
            val viewModel = hiltViewModel<AlbumDetailsViewModel>()
            AlbumDetailsScreen(viewModel)
        }
    }
}