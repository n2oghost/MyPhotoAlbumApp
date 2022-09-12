package com.incentro.myphotoalbum

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.incentro.myphotoalbum.ui.navigation.MyPhotoAlbumNavHost
import org.junit.Before
import org.junit.Rule

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController
    private var invokedNavigationRoute = ""
    private val navigateTo: (String) -> Unit = { route ->
        invokedNavigationRoute = route
    }

    @Before
    fun setupMyAlbumNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            MyPhotoAlbumNavHost(
                navController = navController,
                navigateTo = navigateTo
            )
        }
    }

}