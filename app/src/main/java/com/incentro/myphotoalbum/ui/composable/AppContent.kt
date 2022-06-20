package com.incentro.myphotoalbum.ui.composable

import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.incentro.myphotoalbum.ui.navigation.NavGraph
import com.incentro.core_ui.theme.ComposeAppTheme
import com.incentro.myphotoalbum.R

@Composable
fun AppContent() {
    ComposeAppTheme {
        SmallTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name)
                )
            }
        )
        NavGraph()
    }
}