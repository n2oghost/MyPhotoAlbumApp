package com.incentro.feature_album_overview.data.model.mapper

import com.incentro.core_db.model.AlbumDatabaseModel
import com.incentro.feature_album_overview.data.model.network.AlbumNetworkModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapperExtensionsTests {

    @Test
    fun `AlbumNetworkModel asDatabaseModel() fills all fields correctly`() {
        val networkModel = AlbumNetworkModel(
            id = 1,
            userId = 2,
            title = "3"
        )
        val result = networkModel.asDatabaseModel()

        assertEquals(networkModel.id, result.id)
        assertEquals(networkModel.userId, result.userId)
        assertEquals(networkModel.title, result.title)
    }

    @Test
    fun `AlbumDatabaseModel asAlbum() fills all fields correctly`() {
        val databaseModel = AlbumDatabaseModel(
            id = 1,
            userId = 2,
            title = "3",
            favorite = true
        )
        val result = databaseModel.asAlbum()

        assertEquals(databaseModel.id, result.id)
        assertEquals(databaseModel.favorite, result.favorite)
        assertEquals(databaseModel.title, result.title)
    }
}