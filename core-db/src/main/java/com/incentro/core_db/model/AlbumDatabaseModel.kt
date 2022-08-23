package com.incentro.core_db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class AlbumDatabaseModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val title: String,
    @ColumnInfo(name = "user")
    val userId: Int,
    val favorite: Boolean
)