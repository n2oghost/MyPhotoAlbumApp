package com.incentro.feature_album_overview.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_overview.data.model.Album

const val ALBUM_OVERVIEW_ITEM_TEST_TAG = "album_overview_item_test_tag"

@Composable
fun AlbumOverviewItem(
    item: Album,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navigateTo.invoke(
                    GlobalDestinations.FeatureAlbumDetails.withArguments(item.id)
                )
            }
            .testTag(ALBUM_OVERVIEW_ITEM_TEST_TAG)
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(8.dp),
        )
    }
}