package com.incentro.myphotoalbum.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_detail.ui.navigation.albumDetailsGraph
import com.incentro.feature_album_overview.ui.navigation.albumOverviewGraph

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = GlobalDestinations.FeatureAlbumOverview.route) {
        albumOverviewGraph(navController)
        albumDetailsGraph()
    }
}