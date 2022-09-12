package com.incentro.feature_album_detail.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.incentro.core_ui.navigation.Destination

sealed class Destinations {
    object AlbumDetails : Destination {
        override val route = "album-details"
        const val albumIdArg = "id"
        val routeWithArgs = "${route}/{${albumIdArg}}"
        var arguments = listOf(
            navArgument(name = albumIdArg) {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    }
}
