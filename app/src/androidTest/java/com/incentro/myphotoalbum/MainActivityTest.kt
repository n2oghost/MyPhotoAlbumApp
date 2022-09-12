package com.incentro.myphotoalbum

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import com.incentro.myphotoalbum.ui.activity.MainActivity
import com.incentro.myphotoalbum.ui.navigation.APP_NAV_GRAPH_TEST_TAG
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun mainActivityDisplaysAppContent() {
        ActivityScenario.launch(MainActivity::class.java).use {
            composeTestRule
                .onNodeWithTag(APP_NAV_GRAPH_TEST_TAG)
                .assertIsDisplayed()

            composeTestRule
                .onNodeWithText(text = "My Photo Album", ignoreCase = true)
                .assertIsDisplayed()
        }
    }

}