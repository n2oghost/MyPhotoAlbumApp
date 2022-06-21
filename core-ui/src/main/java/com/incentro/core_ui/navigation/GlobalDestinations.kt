package com.incentro.core_ui.navigation

sealed class GlobalDestinations {
    object FeatureAlbumOverview : Destination(
        route = "feature-album-overview"
    )
    object FeatureAlbumDetails : Destination(
        route = "feature-album-details/{id}"
    ) {
        fun withArguments(id: Int) : String {
            return "feature-album-details/$id"
        }
    }
}
