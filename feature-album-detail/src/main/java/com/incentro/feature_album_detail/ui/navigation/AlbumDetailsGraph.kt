package com.incentro.feature_album_detail.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_detail.ui.composable.AlbumDetailsScreen
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel

const val NAV_ARG_ALBUM_DETAILS_ID = "id"

@OptIn(ExperimentalLifecycleComposeApi::class)
fun NavGraphBuilder.albumDetailsGraph(
    modifier: Modifier = Modifier
) {
    navigation(
        startDestination = Destinations.AlbumDetails.route,
        route = GlobalDestinations.FeatureAlbumDetails.route,
        arguments = listOf(
            navArgument(name = NAV_ARG_ALBUM_DETAILS_ID) {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) {
        composable(
            route = Destinations.AlbumDetails.route,
            arguments = listOf(
                navArgument(name = NAV_ARG_ALBUM_DETAILS_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val viewModel = hiltViewModel<AlbumDetailsViewModel>()
            val viewState by viewModel.viewState.collectAsStateWithLifecycle()
            AlbumDetailsScreen(
                viewState.photos,
                viewState.loadingState,
                modifier
            )
        }
    }
}