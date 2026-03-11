package com.example.pbl.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pbl.ui.components.*
import com.example.pbl.ui.theme.*

@Composable
fun LearnPage(NavController: NavController) {
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