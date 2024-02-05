package com.incentro.feature_album_overview.data.repository

import com.incentro.core_db.dao.AlbumDao
import com.incentro.core_db.model.AlbumDatabaseModel
import com.incentro.feature_album_overview.data.model.network.AlbumNetworkModel
import com.incentro.feature_album_overview.data.service.AlbumOverviewService
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

class AlbumOverviewRepositoryTests {

    private val service = mockk<AlbumOverviewService>(relaxed = true)
    private val albumDao = mockk<AlbumDao>(relaxed = true)

    @Before
    fun beforeTest() {
        coEvery { service.getAlbums() } returns getNetworkAlbumsMock()
        every { albumDao.getAlbums() } returns flow { emit(getLocalAlbumsMock()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchLatestAlbums() calls service and saves data`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)

        val repository = AlbumOverviewRepository(service, albumDao, testDispatcher)

        repository.fetchLatestAlbums()

        coVerify(exactly = 1) {
            service.getAlbums()
            albumDao.insertAllAlbums(any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `val albums gets and maps local data`() = runTest {
        val repository = AlbumOverviewRepository(service, albumDao)

        val result = repository.albums.first()

        verify(exactly = 1) {
            albumDao.getAlbums()
        }
        assertEquals(getLocalAlbumsMock().size, result.size)
    }

    private fun getNetworkAlbumsMock() : List<AlbumNetworkModel> = listOf(
        AlbumNetworkModel(
            userId = 1,
            id = 1,
            title = "hurp"
        ),
        AlbumNetworkModel(
            userId = 2,
            id = 2,
            title = "derp"
        )
    )

    private fun getLocalAlbumsMock() : List<AlbumDatabaseModel> = listOf(
        AlbumDatabaseModel(
            userId = 1,
            id = 1,
            title = "hurp",
            favorite = false
        ),
        AlbumDatabaseModel(
            userId = 2,
            id = 2,
            title = "derp",
            favorite = true
        )
    )
}