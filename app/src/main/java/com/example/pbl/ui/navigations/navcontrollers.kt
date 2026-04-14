package com.example.pbl.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.google.firebase.auth.FirebaseAuth
import com.example.pbl.ui.layout.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentUser = FirebaseAuth.getInstance().currentUser

    val startDestination = if (currentUser == null) "landing" else "home"

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

        composable("home") {
            HomePage(navController)
        }

        composable("learn") {
            LearnPage(navController)
        }
    }
}
