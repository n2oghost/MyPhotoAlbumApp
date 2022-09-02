package com.incentro.myphotoalbum.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_detail.ui.navigation.albumDetailsGraph
import com.incentro.feature_album_overview.ui.navigation.albumOverviewGraph

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = GlobalDestinations.FeatureAlbumOverview.route,
        modifier = modifier
    ) {
        albumOverviewGraph(navController, modifier)
        albumDetailsGraph(modifier)
    }
}