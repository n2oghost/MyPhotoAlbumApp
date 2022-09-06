package com.incentro.feature_album_overview.domain

import com.incentro.feature_album_overview.data.repository.AlbumsRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LoadLatestAlbumsUseCaseTests {

    private val repository = mockk<AlbumsRepository>(relaxed = true)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke() calls correct repository function`() = runTest {
        val useCase = LoadLatestAlbumsUseCase(repository)

        useCase()

        coVerify(exactly = 1) { repository.fetchLatestAlbums() }
    }
}