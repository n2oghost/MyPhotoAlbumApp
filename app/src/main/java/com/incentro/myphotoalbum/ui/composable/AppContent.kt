package com.incentro.myphotoalbum.ui.composable

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.incentro.myphotoalbum.ui.navigation.NavGraph
import com.incentro.core_ui.theme.ComposeAppTheme
import com.incentro.myphotoalbum.R

@Composable
fun AppContent() {
    ComposeAppTheme {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name)
                )
            }
        )
        NavGraph()
    }
}