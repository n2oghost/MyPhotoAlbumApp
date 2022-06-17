package com.incentro.myphotoalbum

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
import com.incentro.core_ui.Deeplinks
import com.incentro.feature_album_overview.ui.model.AlbumUiModel

@Composable
fun AlbumOverviewItem(item: AlbumUiModel, navController: NavController) {
    Surface(
        modifier = Modifier.clickable {
            navController.navigate(Deeplinks.ALBUM_DETAILS)
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