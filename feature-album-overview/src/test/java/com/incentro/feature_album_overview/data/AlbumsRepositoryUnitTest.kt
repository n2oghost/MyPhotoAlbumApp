package com.incentro.feature_album_overview.data

import com.incentro.feature_album_overview.data.model.AlbumDataModel
import com.incentro.feature_album_overview.data.repository.AlbumsRepository
import com.incentro.feature_album_overview.data.service.AlbumsService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class AlbumsRepositoryUnitTest {

    private val service = mockk<AlbumsService>(relaxed = true)
    private val repository = AlbumsRepository(service)

    @Before
    fun beforeTest() {
        coEvery { service.getAlbums() } returns getAlbumsMock()
    }

    @Test
    fun `getAlbums() calls service`() = runTest {
        val expected = getAlbumsMock()
        val result = repository.getAlbums()

        assertEquals(expected, result)
    }

    private fun getAlbumsMock() : List<AlbumDataModel> = listOf(
        AlbumDataModel(
            userId = 1,
            id = 1,
            title = "hurp"
        ),
        AlbumDataModel(
            userId = 2,
            id = 2,
            title = "derp"
        )
    )
}