package com.incentro.core_ui

import androidx.compose.material.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.incentro.core_ui.composable.LoadingScreen
import org.junit.Rule
import org.junit.Test

class LoadingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val progressIndicatorTestTag = "myProgressIndicator"

    @Test
    fun loadingStateDisplaysCorrectViews() {
        val testText = "Content!"

        composeTestRule.setContent {
            LoadingScreen(isLoading = true) {
                Text(text = testText)
            }
        }

        composeTestRule
            .onNodeWithTag(testTag = progressIndicatorTestTag)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(text = testText)
            .assertIsDisplayed()
    }

    @Test
    fun nonLoadingStateDisplaysCorrectViews() {
        val testText = "Content!"

        composeTestRule.setContent {
            LoadingScreen(isLoading = false) {
                Text(text = testText)
            }
        }

        composeTestRule
            .onNodeWithTag(testTag = progressIndicatorTestTag)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(text = testText)
            .assertIsDisplayed()
    }

}