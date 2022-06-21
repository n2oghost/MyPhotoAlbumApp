package com.incentro.myphotoalbum.ui.composable

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.incentro.core_ui.theme.ComposeAppTheme
import com.incentro.myphotoalbum.R
import com.incentro.myphotoalbum.ui.navigation.NavGraph

@Composable
fun AppContent() {
    ComposeAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name)
                        )
                    }
                )
            }
        ) { padding ->
            NavGraph(padding)
        }
    }
}