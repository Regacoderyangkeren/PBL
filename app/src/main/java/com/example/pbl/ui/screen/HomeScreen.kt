package com.example.pbl.ui.screen

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
fun HomePage(navController: NavController, innerPadding: PaddingValues) {
    val auth = FirebaseAuth.getInstance()

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
                text = homeTitle,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            AppFilledButton(
                onClick = {
                    auth.signOut()
                    navController.navigate("landing") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            ) {
                Text(logoutButton)
            }
        }
    }
}