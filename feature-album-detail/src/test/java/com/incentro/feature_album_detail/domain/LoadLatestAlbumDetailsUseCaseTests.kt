package com.incentro.feature_album_detail.domain

import com.incentro.feature_album_detail.data.repository.AlbumDetailsRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LoadLatestAlbumDetailsUseCaseTests {

    private val repository = mockk<AlbumDetailsRepository>(relaxed = true)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke() calls correct repository function`() = runTest {
        val testAlbumId = 1

        val useCase = LoadLatestAlbumDetailsUseCase(repository)

        useCase(testAlbumId)

        coVerify(exactly = 1) { repository.fetchLatestAlbumById(testAlbumId) }
    }
}