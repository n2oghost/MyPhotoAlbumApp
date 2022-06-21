package com.incentro.myphotoalbum.ui.composable

import androidx.compose.runtime.Composable
import com.incentro.core_ui.theme.ComposeAppTheme
import com.incentro.myphotoalbum.ui.navigation.NavGraph

@Composable
fun AppContent() {
    ComposeAppTheme {
        NavGraph()
    }
}