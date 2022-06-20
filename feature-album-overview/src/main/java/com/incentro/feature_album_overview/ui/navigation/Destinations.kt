package com.incentro.feature_album_overview.ui.navigation

import com.incentro.core_ui.navigation.Destination

sealed class Destinations {
    object AlbumOverview : Destination(
        route = "album-overview"
    )
}
