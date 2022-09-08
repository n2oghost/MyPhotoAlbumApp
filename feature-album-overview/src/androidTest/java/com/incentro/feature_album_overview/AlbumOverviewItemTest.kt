package com.incentro.feature_album_overview

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.incentro.core_ui.navigation.GlobalDestinations
import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.ui.composable.ALBUM_OVERVIEW_ITEM_TEST_TAG
import com.incentro.feature_album_overview.ui.composable.AlbumOverviewItem
import org.junit.Rule
import org.junit.Test

class AlbumOverviewItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun listDisplaysItems() {
        val mockData = getAlbumMock()
        var destination: String? = null

        composeTestRule.setContent {
            AlbumOverviewItem(
                navigateTo = { destination = it },
                item = mockData
            )
        }

        composeTestRule
            .onNodeWithText(mockData.title)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(ALBUM_OVERVIEW_ITEM_TEST_TAG)
            .assertHasClickAction()
            .performClick()

        assert(destination == GlobalDestinations.FeatureAlbumDetails.withArguments(mockData.id))
    }

    private fun getAlbumMock() = Album(
        id = 1,
        title = "1",
        favorite = true
    )

}