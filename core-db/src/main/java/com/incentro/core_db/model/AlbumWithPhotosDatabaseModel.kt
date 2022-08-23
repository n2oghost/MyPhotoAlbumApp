package com.incentro.core_db.model

import androidx.room.Embedded
import androidx.room.Relation

data class AlbumWithPhotosDatabaseModel(
    @Embedded
    val album: AlbumDatabaseModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "album"
    )
    val photos: List<PhotoDatabaseModel>
)