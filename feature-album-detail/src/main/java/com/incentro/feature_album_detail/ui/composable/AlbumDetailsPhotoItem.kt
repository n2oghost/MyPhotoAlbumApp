package com.incentro.feature_album_detail.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.incentro.feature_album_detail.ui.model.PhotoUiModel

@Composable
fun AlbumDetailsPhotoItem(item: PhotoUiModel) {
    Box {
        AsyncImage(
            model = item.url,
            contentDescription = null,
            modifier = Modifier.height(400.dp),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = item.title,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomStart)
        )
    }
    Divider(
        color = Color.Gray,
        thickness = 1.dp
    )
}