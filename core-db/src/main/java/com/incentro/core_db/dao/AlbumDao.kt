package com.incentro.core_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.incentro.core_db.model.AlbumDatabaseModel
import com.incentro.core_db.model.AlbumWithPhotosDatabaseModel
import com.incentro.core_db.model.PhotoDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    @Update
    suspend fun update(album: AlbumDatabaseModel)

    @Update
    suspend fun update(photo: PhotoDatabaseModel)

    @Query("SELECT * from photo WHERE album = :id")
    fun getPhotos(id: Int): Flow<List<PhotoDatabaseModel>>

    @Transaction
    @Query("SELECT * from album WHERE id = :id")
    fun getAlbumWithPhotos(id: Int): Flow<AlbumWithPhotosDatabaseModel>

    @Query("SELECT * from album")
    fun getAlbums(): Flow<List<AlbumDatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlbums(albums: List<AlbumDatabaseModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPhotos(albums: List<PhotoDatabaseModel>)
}