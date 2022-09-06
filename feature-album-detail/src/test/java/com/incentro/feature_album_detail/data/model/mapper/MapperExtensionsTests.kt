package com.incentro.feature_album_detail.data.model.mapper

import com.incentro.core_db.model.PhotoDatabaseModel
import com.incentro.feature_album_detail.data.model.asDatabaseModel
import com.incentro.feature_album_detail.data.model.asPhoto
import com.incentro.feature_album_detail.data.model.network.PhotoNetworkModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapperExtensionsTests {

    @Test
    fun `PhotoNetworkModel asDatabaseModel() fills all fields correctly`() {
        val networkModel = PhotoNetworkModel(
            id = 1,
            albumId = 2,
            title = "3",
            url = "4",
            thumbnailUrl = "5"
        )
        val result = networkModel.asDatabaseModel()

        assertEquals(networkModel.id, result.id)
        assertEquals(networkModel.albumId, result.albumId)
        assertEquals(networkModel.title, result.title)
        assertEquals(networkModel.url, result.url)
        assertEquals(networkModel.thumbnailUrl, result.thumbnailUrl)
    }

    @Test
    fun `PhotoDatabaseModel asPhoto() fills all fields correctly`() {
        val databaseModel = PhotoDatabaseModel(
            id = 1,
            albumId = 2,
            title = "3",
            url = "4",
            thumbnailUrl = "5"
        )
        val result = databaseModel.asPhoto()

        assertEquals(databaseModel.id, result.id)
        assertEquals(databaseModel.albumId, result.albumId)
        assertEquals(databaseModel.title, result.title)
        assertEquals(databaseModel.url, result.url)
    }
}