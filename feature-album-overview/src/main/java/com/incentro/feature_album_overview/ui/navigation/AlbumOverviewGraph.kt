package com.incentro.feature_album_overview.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_overview.ui.composable.AlbumOverviewScreen

fun NavGraphBuilder.albumOverviewGraph(
    navigateTo: (String) -> Unit
) {
    navigation(
        startDestination = Destinations.AlbumOverview.route,
        route = GlobalDestinations.FeatureAlbumOverview.route
    ) {
        composable(
            route = Destinations.AlbumOverview.route
        ) {
            AlbumOverviewScreen(
                navigateTo = navigateTo
            )
        }
    }
}