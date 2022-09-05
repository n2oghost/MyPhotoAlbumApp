package com.incentro.feature_album_detail.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.incentro.feature_album_detail.data.model.Photo

@Composable
fun AlbumDetailsPhotoItem(
    item: Photo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box {
            AsyncImage(
                model = item.url,
                contentDescription = null,
                modifier = Modifier.height(400.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            )
        }
    }
}