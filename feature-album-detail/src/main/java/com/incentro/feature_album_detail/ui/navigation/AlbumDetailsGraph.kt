package com.incentro.feature_album_detail.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_detail.ui.composable.AlbumDetailsScreen
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
fun NavGraphBuilder.albumDetailsGraph(
    modifier: Modifier = Modifier
) {
    navigation(
        startDestination = Destinations.AlbumDetails.routeWithArgs,
        route = GlobalDestinations.FeatureAlbumDetails.routeWithArgs,
        arguments = GlobalDestinations.FeatureAlbumDetails.arguments
    ) {
        composable(
            route = Destinations.AlbumDetails.routeWithArgs,
            arguments = Destinations.AlbumDetails.arguments
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