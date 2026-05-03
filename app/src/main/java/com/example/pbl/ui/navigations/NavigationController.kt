package com.example.pbl.ui.navigations

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.*
import com.google.firebase.auth.FirebaseAuth
import com.example.pbl.ui.animation.*
import com.example.pbl.ui.components.AppScaffold
import com.example.pbl.ui.components.AppSnackbarHost
import com.example.pbl.ui.screen.*
import com.example.pbl.ui.theme.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val snackbarHostState = remember { SnackbarHostState() }

    val startDestination = remember {
        when {
            currentUser == null -> "landing"
            !currentUser.isEmailVerified -> "verify"
            else -> "home"
        }
    }

    // Track current route to drive the top bar
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Which routes show a back button
    val routesWithBack = setOf("login", "register", "forgotpassword", "verify", "learn", "terms")

    AppScaffold(
        navController = navController,
        title = when (currentRoute) {
            "terms" -> termsTitle
            else -> ""
        },
        navigationIcon = {
            if (currentRoute in routesWithBack) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        snackbarHost = { AppSnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            enterTransition = { PushEnter },
            exitTransition = { PushExit },
            popEnterTransition = { PopEnter },
            popExitTransition = { PopExit }
        ) {
            composable(
                route = "landing",
                enterTransition = { noEnter },
                exitTransition = { PushExit },
                popEnterTransition = { PopEnter },
                popExitTransition = { noExit }
            ) {
                LandingPage(navController, innerPadding)
            }

            composable(
                route = "login",
                enterTransition = { PushEnter },
                exitTransition = { PushExit },
                popEnterTransition = { PopEnter },
                popExitTransition = { PopExit }
            ) {
                SignInPage(navController, innerPadding, snackbarHostState)
            }

            composable(
                route = "register",
                enterTransition = { PushEnter },
                exitTransition = { PushExit },
                popEnterTransition = { PopEnter },
                popExitTransition = { PopExit }
            ) {
                SignUpPage(navController, innerPadding, snackbarHostState)
            }

            composable(
                route = "verify",
                enterTransition = { fadeEnter },
                exitTransition = { fadeExit },
                popEnterTransition = { fadeEnter },
                popExitTransition = { fadeExit }
            ) {
                EmailVerifyPage(navController, innerPadding, snackbarHostState)
            }

            composable(
                route = "forgotpassword",
                enterTransition = { PushEnter },
                exitTransition = { PushExit },
                popEnterTransition = { PopEnter },
                popExitTransition = { PopExit }
            ) {
                ForgotPasswordPage(navController, innerPadding, snackbarHostState)
            }

            composable(
                route = "home",
                enterTransition = { fadeEnter },
                exitTransition = { fadeExit },
                popEnterTransition = { fadeEnter },
                popExitTransition = { fadeExit }
            ) {
                HomePage(navController, innerPadding)
            }

            composable(
                route = "learn",
                enterTransition = { slideUpEnter },
                exitTransition = { slideUpExit },
                popEnterTransition = { slideDownEnter },
                popExitTransition = { slideDownExit }
            ) {
                LearnPage(navController, innerPadding)
            }

            composable(
                route = "terms",
                enterTransition = { slideUpEnter },
                exitTransition = { slideUpExit },
                popEnterTransition = { slideDownEnter },
                popExitTransition = { slideDownExit }
            ) {
                TermsAndConditionsPage(navController, innerPadding)
            }
        }
    }
}