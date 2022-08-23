package com.incentro.feature_album_overview.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_overview.data.model.Album

@Composable
fun AlbumOverviewItem(item: Album, navController: NavController) {
    Surface(
        modifier = Modifier.clickable {
            navController.navigate(
                route = GlobalDestinations.FeatureAlbumDetails.withArguments(item.id)
            )
        }
    ) {
        Text(
            text = item.title,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
        Divider(
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}