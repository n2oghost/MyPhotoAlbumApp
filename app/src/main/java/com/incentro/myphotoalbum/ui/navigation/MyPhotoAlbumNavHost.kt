package com.incentro.myphotoalbum.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_detail.ui.navigation.albumDetailsGraph
import com.incentro.feature_album_overview.ui.navigation.albumOverviewGraph

@Composable
fun MyPhotoAlbumNavHost(
    navigateTo: (String) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = GlobalDestinations.FeatureAlbumOverview.route,
        modifier = modifier
    ) {
        albumOverviewGraph(navigateTo, modifier)
        albumDetailsGraph(modifier)
    }
}