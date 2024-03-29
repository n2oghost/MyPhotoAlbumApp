package com.incentro.feature_album_overview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.ui.composable.ALBUM_LIST_TEST_TAG
import com.incentro.feature_album_overview.ui.composable.AlbumOverviewList
import com.incentro.feature_album_overview.ui.composable.AlbumOverviewScreen
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiLoadingState
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiState
import com.incentro.feature_album_overview.ui.viewmodel.AlbumOverviewViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class AlbumOverviewScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun listDisplaysItems() {
        val mockData = getAlbumsMock()

        composeTestRule.setContent {
            AlbumOverviewList(
                albums = mockData,
                navigateTo = { }
            )
        }

        composeTestRule
            .onNodeWithTag(ALBUM_LIST_TEST_TAG)
            .performScrollToIndex(mockData.lastIndex)

        composeTestRule
            .onNodeWithText(mockData.last().title)
            .assertIsDisplayed()
    }

    @Test
    fun screenShowsList() {
        val mockState = AlbumOverviewUiState(
            albums = getAlbumsMock(),
            loadingState = AlbumOverviewUiLoadingState.Success
        )
        val mockViewModel = mockk<AlbumOverviewViewModel>(relaxed = true)
        every { mockViewModel.viewState.value } returns mockState

        composeTestRule.setContent {
            AlbumOverviewScreen(
                viewModel = mockViewModel,
                navigateTo = { }
            )
        }

        composeTestRule
            .onNodeWithTag(ALBUM_LIST_TEST_TAG)
            .assertIsDisplayed()
    }

    private fun getAlbumsMock() = (0 until 50).map {
        Album(
            id = it,
            title = "$it",
            favorite = true
        )
    }

}