package com.incentro.feature_album_detail.ui.navigation

import com.incentro.core_ui.navigation.Destination

sealed class Destinations {
    object AlbumDetails : Destination(
        route = "album-details/{id}"
    ) {
        fun withArguments(id: Int) : String {
            return "album-details/$id"
        }
    }
}
