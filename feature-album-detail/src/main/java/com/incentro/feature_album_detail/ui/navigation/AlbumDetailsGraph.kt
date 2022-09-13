package com.incentro.feature_album_detail.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_detail.ui.composable.AlbumDetailsScreen

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
            AlbumDetailsScreen(
                modifier
            )
        }
    }
}