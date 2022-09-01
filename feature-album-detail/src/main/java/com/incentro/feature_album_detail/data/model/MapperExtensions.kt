package com.incentro.feature_album_detail.data.model

import com.incentro.core_db.model.PhotoDatabaseModel
import com.incentro.feature_album_detail.data.model.network.PhotoNetworkModel

fun PhotoNetworkModel.asDatabaseModel() : PhotoDatabaseModel = PhotoDatabaseModel(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)

fun PhotoDatabaseModel.asPhoto() : Photo = Photo(
    albumId = albumId,
    id = id,
    title = title,
    url = url
)