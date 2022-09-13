package com.incentro.myphotoalbum

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import com.incentro.feature_album_detail.ui.composable.ALBUM_DETAIL_ITEM_PHOTO_TEST_TAG
import com.incentro.feature_album_overview.ui.composable.ALBUM_OVERVIEW_ITEM_TEST_TAG
import com.incentro.myphotoalbum.ui.activity.MainActivity
import org.junit.Rule
import org.junit.Test

const val TIMEOUT = 5_000L

class GlobalAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun overviewItemNavigatesToAlbumDetails() {
        composeTestRule.waitUntil(
            timeoutMillis = TIMEOUT,
            condition = {
                composeTestRule
                    .onAllNodesWithTag(ALBUM_OVERVIEW_ITEM_TEST_TAG)
                    .fetchSemanticsNodes()
                    .isNotEmpty()
            })

        composeTestRule
            .onAllNodesWithTag(ALBUM_OVERVIEW_ITEM_TEST_TAG)
            .onFirst()
            .performClick()

        composeTestRule.waitUntil(
            timeoutMillis = TIMEOUT,
            condition = {
                composeTestRule
                    .onAllNodesWithTag(ALBUM_DETAIL_ITEM_PHOTO_TEST_TAG)
                    .fetchSemanticsNodes()
                    .isNotEmpty()
            })
    }

}