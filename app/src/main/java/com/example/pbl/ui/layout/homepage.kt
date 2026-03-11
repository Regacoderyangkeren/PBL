package com.example.pbl.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pbl.ui.theme.*
import com.example.pbl.ui.components.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomePage(navController: NavController) {
    // Get the Firebase Auth instance
    val auth = FirebaseAuth.getInstance()

    Scaffolds(navController = navController) { innerPadding: PaddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = landingPage2,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Temporary Logout Button
                AppFilledButton(
                    text = "Logout",
                    onClick = {
                        auth.signOut()
                        navController.navigate("landingpage") {
                            popUpTo("homepage") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}