package com.incentro.feature_album_overview.data.model.mapper

import com.incentro.core_db.model.AlbumDatabaseModel
import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.data.model.network.AlbumNetworkModel

fun AlbumNetworkModel.asDatabaseModel() : AlbumDatabaseModel = AlbumDatabaseModel(
    id = id,
    title = title,
    userId = userId,
    favorite = false // The mock service does not support favorites
)

fun AlbumDatabaseModel.asAlbum() : Album = Album(
    id = id,
    title = title,
    favorite = favorite
)