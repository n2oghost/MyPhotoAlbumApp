package com.incentro.feature_album_overview.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_overview.data.model.Album

@Composable
fun AlbumOverviewItem(
    item: Album,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    route = GlobalDestinations.FeatureAlbumDetails.withArguments(item.id)
                )
            }
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(8.dp),
        )
    }
}