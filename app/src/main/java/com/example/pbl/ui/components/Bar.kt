package com.example.pbl.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    navController: NavController,
    title: String = "",
    // Slot untuk yang ada di pojok kanan atas
    actions: @Composable RowScope.() -> Unit = {},
    // Slot untuk ikon navigasi
    navigationIcon: @Composable () -> Unit = {},
    // slot untuk bottom bar
    bottomBar: @Composable () -> Unit = {},
    // slot untuk snackbar (optional, pakai AppSnackbarHost)
    snackbarHost: @Composable () -> Unit = {},
    // konten utama
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                windowInsets = TopAppBarDefaults.windowInsets,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text(text = title) },
                navigationIcon = navigationIcon,
                actions = actions
            )
        },
        bottomBar = {
            Box(Modifier.navigationBarsPadding()) {
                bottomBar()
            }
        },
        snackbarHost = snackbarHost,
        content = { innerPadding ->
            content(innerPadding)
        }
    )
}