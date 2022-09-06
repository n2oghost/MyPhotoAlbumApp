package com.incentro.feature_album_overview.ui

import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.domain.GetLocalAlbumsUseCase
import com.incentro.feature_album_overview.domain.LoadLatestAlbumsUseCase
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiLoadingState
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiState
import com.incentro.feature_album_overview.ui.viewmodel.AlbumOverviewViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AlbumOverviewViewModelTests {

    private val getLocalAlbumsUseCase = mockk<GetLocalAlbumsUseCase>(relaxed = true)
    private val loadLatestAlbumsUseCase = mockk<LoadLatestAlbumsUseCase>(relaxed = true)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `is initialised with default state`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)

        val viewModel = AlbumOverviewViewModel(
            getLocalAlbumsUseCase = getLocalAlbumsUseCase,
            loadLatestAlbumsUseCase = loadLatestAlbumsUseCase,
            dispatcher = testDispatcher
        )

        assertEquals(AlbumOverviewUiState().loadingState, viewModel.viewState.value.loadingState)
        assertEquals(AlbumOverviewUiState().albums, viewModel.viewState.value.albums)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `init gets local data and updates state`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val testData = listOf(
            Album(1, "2", true)
        )

        coEvery { (getLocalAlbumsUseCase::invoke)() } returns flow {
            emit(testData)
        }

        val viewModel = AlbumOverviewViewModel(
            getLocalAlbumsUseCase = getLocalAlbumsUseCase,
            loadLatestAlbumsUseCase = loadLatestAlbumsUseCase,
            dispatcher = testDispatcher
        )

        val results = mutableListOf<AlbumOverviewUiState>()
        val job = launch(testDispatcher) { viewModel.viewState.toList(results) }

        runCurrent()

        assert(results.isNotEmpty())
        assert(results[0].albums.isNotEmpty())
        assertEquals(testData.size, results[0].albums.size)
        assertEquals(testData[0].title, results[0].albums[0].title)
        coVerify(exactly = 1) { getLocalAlbumsUseCase.invoke() }

        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `init executes loadLatestAlbumOverviewUseCase and updates state accordingly`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)

        val viewModel = AlbumOverviewViewModel(
            getLocalAlbumsUseCase = getLocalAlbumsUseCase,
            loadLatestAlbumsUseCase = loadLatestAlbumsUseCase,
            dispatcher = testDispatcher
        )

        val results = mutableListOf<AlbumOverviewUiState>()
        val job = launch(testDispatcher) { viewModel.viewState.toList(results) }

        runCurrent()

        assert(results.isNotEmpty())
        assertEquals(AlbumOverviewUiLoadingState.Success, results[0].loadingState)
        coVerify(exactly = 1) { loadLatestAlbumsUseCase.invoke() }

        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `init updates state correctly after loadLatestAlbumOverviewUseCase fails`() = runTest {
        val testErrorMessage = "oopsie woopsie"
        val testDispatcher = StandardTestDispatcher(testScheduler)

        coEvery {
            (loadLatestAlbumsUseCase::invoke)()
        } throws RuntimeException(testErrorMessage)

        val viewModel = AlbumOverviewViewModel(
            getLocalAlbumsUseCase = getLocalAlbumsUseCase,
            loadLatestAlbumsUseCase = loadLatestAlbumsUseCase,
            dispatcher = testDispatcher
        )

        val results = mutableListOf<AlbumOverviewUiState>()
        val job = launch(testDispatcher) { viewModel.viewState.toList(results) }

        runCurrent()

        assert(results.isNotEmpty())
        assert(results[0].loadingState is AlbumOverviewUiLoadingState.Error)
        assertEquals(
            testErrorMessage,
            (results[0].loadingState as AlbumOverviewUiLoadingState.Error).errorMessage
        )
        coVerify(exactly = 1) { loadLatestAlbumsUseCase.invoke() }

        job.cancel()
    }

}