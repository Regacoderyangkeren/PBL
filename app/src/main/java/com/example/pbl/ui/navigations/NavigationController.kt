package com.example.pbl.ui.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.*
import com.google.firebase.auth.FirebaseAuth
import com.example.pbl.ui.screen.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentUser = FirebaseAuth.getInstance().currentUser

    val startDestination = remember {
        when {
            currentUser == null -> "landing"
            !currentUser.isEmailVerified -> "verify"
            else -> "home"
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("landing") {
            LandingPage(navController)
        }

        composable("login") {
            SignInPage(navController)
        }

        composable("register") {
            SignUpPage(navController)
        }

        composable("verify") {
            EmailVerifyPage(navController)
        }

        composable("forgotpassword") {
            ForgotPasswordPage(navController)
        }

        composable("home") {
            HomePage(navController)
        }

        composable("learn") {
            LearnPage(navController)
        }

        composable("terms") {
            TermsAndConditionsPage(navController)
        }
    }
}