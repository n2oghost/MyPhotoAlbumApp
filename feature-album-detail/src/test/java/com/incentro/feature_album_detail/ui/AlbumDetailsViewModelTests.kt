package com.incentro.feature_album_detail.ui

import androidx.lifecycle.SavedStateHandle
import com.incentro.feature_album_detail.data.model.Photo
import com.incentro.feature_album_detail.domain.GetLocalAlbumDetailsUseCase
import com.incentro.feature_album_detail.domain.LoadLatestAlbumDetailsUseCase
import com.incentro.feature_album_detail.ui.navigation.NAV_ARG_ALBUM_DETAILS_ID
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiLoadingState
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiState
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel.Companion.NO_ID_EXCEPTION_MESSAGE
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AlbumDetailsViewModelTests {

    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
    private val getLocalAlbumDetailsUseCase = mockk<GetLocalAlbumDetailsUseCase>(relaxed = true)
    private val loadLatestAlbumDetailsUseCase = mockk<LoadLatestAlbumDetailsUseCase>(relaxed = true)

    @Before
    fun beforeTests() {
        every { savedStateHandle.get<Int>(NAV_ARG_ALBUM_DETAILS_ID) } returns SAVED_STATE_ID_VALUE
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `id is retrieved from savedState handle`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)

        AlbumDetailsViewModel(
            savedStateHandle = savedStateHandle,
            getLocalAlbumDetailsUseCase = getLocalAlbumDetailsUseCase,
            loadLatestAlbumDetailsUseCase = loadLatestAlbumDetailsUseCase,
            dispatcher = testDispatcher
        )

        verify(exactly = 1) { savedStateHandle.get<Int>(NAV_ARG_ALBUM_DETAILS_ID) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `no supplied id throws exception`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)

        every { savedStateHandle.get<Int>(NAV_ARG_ALBUM_DETAILS_ID) } returns null

        val result = try {
            AlbumDetailsViewModel(
                savedStateHandle = savedStateHandle,
                getLocalAlbumDetailsUseCase = getLocalAlbumDetailsUseCase,
                loadLatestAlbumDetailsUseCase = loadLatestAlbumDetailsUseCase,
                dispatcher = testDispatcher
            )
            "nope"
        } catch (e: Exception) {
            e.message
        }

        assertEquals(NO_ID_EXCEPTION_MESSAGE, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `is initialised with default state`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)

        val viewModel = AlbumDetailsViewModel(
            savedStateHandle = savedStateHandle,
            getLocalAlbumDetailsUseCase = getLocalAlbumDetailsUseCase,
            loadLatestAlbumDetailsUseCase = loadLatestAlbumDetailsUseCase,
            dispatcher = testDispatcher
        )

        assertEquals(AlbumDetailsUiState().loadingState, viewModel.viewState.value.loadingState)
        assertEquals(AlbumDetailsUiState().photos, viewModel.viewState.value.photos)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `init gets local data and updates state`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val testData = listOf(
            Photo(1, 2, "3", "4")
        )

        coEvery { (getLocalAlbumDetailsUseCase::invoke)(SAVED_STATE_ID_VALUE) } returns flow {
            emit(testData)
        }

        val viewModel = AlbumDetailsViewModel(
            savedStateHandle = savedStateHandle,
            getLocalAlbumDetailsUseCase = getLocalAlbumDetailsUseCase,
            loadLatestAlbumDetailsUseCase = loadLatestAlbumDetailsUseCase,
            dispatcher = testDispatcher
        )

        val results = mutableListOf<AlbumDetailsUiState>()
        val job = launch(testDispatcher) { viewModel.viewState.toList(results) }

        runCurrent()

        assert(results.isNotEmpty())
        assert(results[0].photos.isNotEmpty())
        assertEquals(testData.size, results[0].photos.size)
        assertEquals(testData[0].title, results[0].photos[0].title)
        coVerify(exactly = 1) { getLocalAlbumDetailsUseCase.invoke(SAVED_STATE_ID_VALUE) }

        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `init executes loadLatestAlbumDetailsUseCase and updates state accordingly`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)

        val viewModel = AlbumDetailsViewModel(
            savedStateHandle = savedStateHandle,
            getLocalAlbumDetailsUseCase = getLocalAlbumDetailsUseCase,
            loadLatestAlbumDetailsUseCase = loadLatestAlbumDetailsUseCase,
            dispatcher = testDispatcher
        )

        val results = mutableListOf<AlbumDetailsUiState>()
        val job = launch(testDispatcher) { viewModel.viewState.toList(results) }

        runCurrent()

        assert(results.isNotEmpty())
        assertEquals(AlbumDetailsUiLoadingState.Success, results[0].loadingState)
        coVerify(exactly = 1) { loadLatestAlbumDetailsUseCase.invoke(SAVED_STATE_ID_VALUE) }

        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `init updates state correctly after loadLatestAlbumDetailsUseCase fails`() = runTest {
        val testErrorMessage = "oopsie woopsie"
        val testDispatcher = StandardTestDispatcher(testScheduler)

        coEvery {
            (loadLatestAlbumDetailsUseCase::invoke)(SAVED_STATE_ID_VALUE)
        } throws RuntimeException(testErrorMessage)

        val viewModel = AlbumDetailsViewModel(
            savedStateHandle = savedStateHandle,
            getLocalAlbumDetailsUseCase = getLocalAlbumDetailsUseCase,
            loadLatestAlbumDetailsUseCase = loadLatestAlbumDetailsUseCase,
            dispatcher = testDispatcher
        )

        val results = mutableListOf<AlbumDetailsUiState>()
        val job = launch(testDispatcher) { viewModel.viewState.toList(results) }

        runCurrent()

        assert(results.isNotEmpty())
        assert(results[0].loadingState is AlbumDetailsUiLoadingState.Error)
        assertEquals(
            testErrorMessage,
            (results[0].loadingState as AlbumDetailsUiLoadingState.Error).errorMessage
        )
        coVerify(exactly = 1) { loadLatestAlbumDetailsUseCase.invoke(SAVED_STATE_ID_VALUE) }

        job.cancel()
    }

    companion object {
        const val SAVED_STATE_ID_VALUE = 666
    }

}