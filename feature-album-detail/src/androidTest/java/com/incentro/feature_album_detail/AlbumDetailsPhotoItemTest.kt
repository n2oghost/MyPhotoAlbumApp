package com.incentro.feature_album_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.incentro.feature_album_detail.data.model.Photo
import com.incentro.feature_album_detail.ui.composable.ALBUM_DETAIL_ITEM_PHOTO_TEST_TAG
import com.incentro.feature_album_detail.ui.composable.AlbumDetailsPhotoItem
import org.junit.Rule
import org.junit.Test

class AlbumDetailsPhotoItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun listDisplaysItems() {
        val mockData = getAlbumMock()

        composeTestRule.setContent {
            AlbumDetailsPhotoItem(
                item = mockData
            )
        }

        composeTestRule
            .onNodeWithText(mockData.title)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(ALBUM_DETAIL_ITEM_PHOTO_TEST_TAG)
            .assertIsDisplayed()
    }

    private fun getAlbumMock() = Photo(
        id = 1,
        title = "1",
        albumId = 2,
        url = "4"
    )

}