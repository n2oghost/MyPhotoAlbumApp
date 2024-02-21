package com.incentro.myphotoalbum.ui.composable

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.incentro.core_ui.theme.MyPhotoAlbumTheme
import com.incentro.myphotoalbum.ui.navigation.MyPhotoAlbumNavHost

@Composable
fun AppContent() {
    MyPhotoAlbumTheme {
        val navController = rememberNavController()
        val navigateTo: (String) -> Unit = {
            navController.navigate(
                route = it
            )
        }
        MyPhotoAlbumNavHost(
            navigateTo = navigateTo,
            navController = navController
        )
    }
}