package com.incentro.feature_album_overview.domain

import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.data.repository.AlbumOverviewRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetLocalAlbumsUseCaseTests {

    private val repository = mockk<AlbumOverviewRepository>(relaxed = true)

    @Before
    fun beforeTest() {
        every { repository.albums } returns flow { emit(getAlbumsMock()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke() returns correct data`() = runTest {
        val useCase = GetLocalAlbumsUseCase(repository)

        val result = useCase()

        verify(exactly = 1) { repository.albums }
        assertEquals(getAlbumsMock().size, result.first().size)
    }

    private fun getAlbumsMock() : List<Album> = listOf(
        Album(
            id = 1,
            title = "hurp",
            favorite = false
        ),
        Album(
            id = 2,
            title = "derp",
            favorite = true
        )
    )
}