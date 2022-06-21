package com.incentro.feature_album_overview.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_overview.ui.composable.AlbumOverviewScreen
import com.incentro.feature_album_overview.ui.viewmodel.AlbumOverviewViewModel

fun NavGraphBuilder.albumOverviewGraph(navController: NavController) {
    navigation(
        startDestination = Destinations.AlbumOverview.route,
        route = GlobalDestinations.FeatureAlbumOverview.route
    ) {
        composable(
            route = Destinations.AlbumOverview.route
        ) {
            val viewModel = hiltViewModel<AlbumOverviewViewModel>()
            AlbumOverviewScreen(viewModel, navController)
        }
    }
}