package com.incentro.myphotoalbum.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_detail.ui.navigation.albumDetailsGraph
import com.incentro.feature_album_overview.ui.navigation.albumOverviewGraph

@Composable
fun NavGraph(paddingValues: PaddingValues) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = GlobalDestinations.FeatureAlbumOverview.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        albumOverviewGraph(navController)
        albumDetailsGraph()
    }
}