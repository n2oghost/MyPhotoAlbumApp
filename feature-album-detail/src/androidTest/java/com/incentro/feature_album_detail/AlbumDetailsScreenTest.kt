package com.incentro.feature_album_detail

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import com.incentro.feature_album_detail.data.model.Photo
import com.incentro.feature_album_detail.ui.composable.AlbumDetailsPhotoList
import com.incentro.feature_album_detail.ui.composable.AlbumDetailsScreen
import com.incentro.feature_album_detail.ui.composable.PHOTO_LIST_TEST_TAG
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiLoadingState
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiState
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class AlbumDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun listDisplaysItems() {
        val mockData = getPhotosMock()

        composeTestRule.setContent {
            AlbumDetailsPhotoList(photos = mockData)
        }

        composeTestRule
            .onNodeWithTag(PHOTO_LIST_TEST_TAG)
            .performScrollToIndex(mockData.lastIndex)
            .onChildren()
            .assertCountEquals(mockData.size)

        composeTestRule
            .onNodeWithText(mockData.last().title)
            .assertIsDisplayed()
    }

    @Test
    fun screenShowsList() {
        val mockState = AlbumDetailsUiState(
            photos = getPhotosMock(),
            loadingState = AlbumDetailsUiLoadingState.Success
        )
        val mockViewModel = mockk<AlbumDetailsViewModel>(relaxed = true)
        every { mockViewModel.viewState.value } returns mockState

        composeTestRule.setContent {
            AlbumDetailsScreen(
                viewModel = mockViewModel
            )
        }

        composeTestRule
            .onNodeWithTag(PHOTO_LIST_TEST_TAG)
            .assertIsDisplayed()
    }

    private fun getPhotosMock() = listOf(
        Photo(
            albumId = 1,
            id = 1,
            url = "1",
            title = "1"
        ),
        Photo(
            albumId = 2,
            id = 2,
            url = "2",
            title = "2"
        ),
        Photo(
            albumId = 3,
            id = 3,
            url = "3",
            title = "3"
        ),
        Photo(
            albumId = 4,
            id = 4,
            url = "4",
            title = "4"
        )
    )

}