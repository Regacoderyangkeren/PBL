package com.example.pbl.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.pbl.ui.theme.*

@Composable
fun LearnPage(navController: NavController, innerPadding: PaddingValues) {
    val title = titleDrop
    val Subtitle = listOf(
        landingPage1,
        landingPage2,
        landingPage3,
        landingPage4
    )
    val Description = listOf(
        landingpagesub1,
        landingpagesub2,
        landingpagesub3,
        landingpagesub4
    )
}