package com.incentro.core_ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.incentro.core_ui.composable.LoadingIndicator
import org.junit.Rule
import org.junit.Test

class LoadingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val progressIndicatorTestTag = "myProgressIndicator"

    @Test
    fun loadingStateDisplaysCorrectViews() {
        composeTestRule.setContent {
            LoadingIndicator(isLoading = true)
        }

        composeTestRule
            .onNodeWithTag(testTag = progressIndicatorTestTag)
            .assertIsDisplayed()
    }

    @Test
    fun nonLoadingStateDisplaysCorrectViews() {
        composeTestRule.setContent {
            LoadingIndicator(isLoading = false)
        }

        composeTestRule
            .onNodeWithTag(testTag = progressIndicatorTestTag)
            .assertDoesNotExist()
    }

}