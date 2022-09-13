package com.incentro.core_ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class GlobalDestinations {
    object FeatureAlbumOverview : Destination {
        override val route = "feature-album-overview"
    }
    object FeatureAlbumDetails : Destination {
        override val route = "feature-album-details"
        const val albumIdArg = "id"
        val routeWithArgs = "${route}/{${albumIdArg}}"
        var arguments = listOf(
            navArgument(name = albumIdArg) {
                type = NavType.IntType
                defaultValue = -1
            }
        )
        fun withArguments(id: Int) : String {
            return "feature-album-details/$id"
        }
    }
}
