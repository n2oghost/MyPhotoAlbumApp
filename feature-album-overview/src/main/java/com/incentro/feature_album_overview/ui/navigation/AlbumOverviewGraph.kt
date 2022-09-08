package com.incentro.feature_album_overview.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_overview.ui.composable.AlbumOverviewScreen
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiState
import com.incentro.feature_album_overview.ui.viewmodel.AlbumOverviewViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
fun NavGraphBuilder.albumOverviewGraph(
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    navigation(
        startDestination = Destinations.AlbumOverview.route,
        route = GlobalDestinations.FeatureAlbumOverview.route
    ) {
        composable(
            route = Destinations.AlbumOverview.route
        ) {
            val viewModel = hiltViewModel<AlbumOverviewViewModel>()
            val viewState: AlbumOverviewUiState by viewModel.viewState.collectAsStateWithLifecycle()
            AlbumOverviewScreen(
                viewState.albums,
                viewState.loadingState,
                navigateTo,
                modifier
            )
        }
    }
}