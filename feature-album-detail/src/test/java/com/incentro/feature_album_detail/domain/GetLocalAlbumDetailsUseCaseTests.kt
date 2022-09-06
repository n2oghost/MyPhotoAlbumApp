package com.incentro.feature_album_detail.domain

import com.incentro.feature_album_detail.data.model.Photo
import com.incentro.feature_album_detail.data.repository.AlbumDetailsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetLocalAlbumDetailsUseCaseTests {

    private val repository = mockk<AlbumDetailsRepository>(relaxed = true)

    @Before
    fun beforeTest() {
        coEvery { repository.getLocalAlbumById(any()) } returns flow { emit(getAlbumMock()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke() returns correct data`() = runTest {
        val testAlbumId = 1

        val useCase = GetLocalAlbumDetailsUseCase(repository)

        val result = useCase(testAlbumId)

        coVerify(exactly = 1) { repository.getLocalAlbumById(testAlbumId) }
        assertEquals(getAlbumMock().size, result.first().size)
    }

    private fun getAlbumMock() : List<Photo> = listOf(
        Photo(
            albumId = 3,
            id = 1,
            title = "hurp",
            url = "555"
        ),
        Photo(
            albumId = 3,
            id = 2,
            title = "derp",
            url = "666"
        )
    )
}