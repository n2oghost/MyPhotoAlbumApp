package com.incentro.feature_album_detail.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel
import com.incentro.feature_album_detail.ui.composable.AlbumDetailsScreen

fun NavGraphBuilder.albumDetailsGraph() {
    navigation(
        startDestination = Destinations.AlbumDetails.route,
        route = GlobalDestinations.FeatureAlbumDetails.route,
        arguments = listOf(
            navArgument(name = "id") {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) {
        composable(
            route = Destinations.AlbumDetails.route,
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val viewModel = hiltViewModel<AlbumDetailsViewModel>()
            AlbumDetailsScreen(viewModel)
        }
    }
}