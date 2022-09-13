package com.incentro.myphotoalbum.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.incentro.core_ui.theme.ComposeAppTheme
import com.incentro.myphotoalbum.R
import com.incentro.myphotoalbum.ui.navigation.MyPhotoAlbumNavHost

@Composable
fun AppContent() {
    ComposeAppTheme {
        val navController = rememberNavController()
        val navigateTo: (String) -> Unit = {
            navController.navigate(
                route = it
            )
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.h1
                        )
                    }
                )
            },
        ) { padding ->
            MyPhotoAlbumNavHost(
                navigateTo = navigateTo,
                navController = navController,
                modifier = Modifier.padding(padding)
            )
        }
    }
}