package com.incentro.feature_album_detail.data.repository

import com.incentro.core_db.dao.AlbumDao
import com.incentro.core_db.model.PhotoDatabaseModel
import com.incentro.feature_album_detail.data.model.network.PhotoNetworkModel
import com.incentro.feature_album_detail.data.service.AlbumDetailsService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AlbumDetailsRepositoryTests {

    private val service = mockk<AlbumDetailsService>(relaxed = true)
    private val albumDao = mockk<AlbumDao>(relaxed = true)

    @Before
    fun beforeTest() {
        coEvery { service.getAlbumById(any()) } returns getNetworkAlbumMock()
        every { albumDao.getPhotos(any()) } returns flow { emit(getLocalAlbumMock()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchLatestAlbumById(id) calls service and saves data`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val testAlbumId = 1

        val repository = AlbumDetailsRepository(service, albumDao, testDispatcher)

        repository.fetchLatestAlbumById(testAlbumId)

        coVerify(exactly = 1) {
            service.getAlbumById(testAlbumId)
            albumDao.insertAllPhotos(any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getLocalAlbumById(id) gets and maps local data`() = runTest {
        val testAlbumId = 1

        val repository = AlbumDetailsRepository(service, albumDao)

        val result = repository.getLocalAlbumById(testAlbumId).first()

        verify(exactly = 1) {
            albumDao.getPhotos(testAlbumId)
        }
        assertEquals(getLocalAlbumMock().size, result.size)
    }

    private fun getNetworkAlbumMock() : List<PhotoNetworkModel> = listOf(
        PhotoNetworkModel(
            albumId = 3,
            id = 1,
            title = "hurp",
            url = "555",
            thumbnailUrl = "1337"
        ),
        PhotoNetworkModel(
            albumId = 3,
            id = 2,
            title = "derp",
            url = "666",
            thumbnailUrl = "42"
        )
    )

    private fun getLocalAlbumMock() : List<PhotoDatabaseModel> = listOf(
        PhotoDatabaseModel(
            albumId = 3,
            id = 1,
            title = "hurp",
            url = "555",
            thumbnailUrl = "1337"
        ),
        PhotoDatabaseModel(
            albumId = 3,
            id = 2,
            title = "derp",
            url = "666",
            thumbnailUrl = "42"
        )
    )
}