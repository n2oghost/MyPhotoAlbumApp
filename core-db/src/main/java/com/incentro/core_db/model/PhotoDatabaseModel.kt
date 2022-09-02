package com.incentro.core_db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "photo",
    foreignKeys = [ForeignKey(
        entity = AlbumDatabaseModel::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("album"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("album")
    ]
)
data class PhotoDatabaseModel(
    @ColumnInfo(name = "album")
    val albumId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val url: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnailUrl: String
)