package com.example.pbl.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pbl.ui.components.*
import com.example.pbl.ui.uihelpers.CardContentWrapper
import com.example.pbl.ui.theme.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignUpPage(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                signUpTitle,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            AppStaticFilledCard(modifier = Modifier.fillMaxWidth()) {
                CardContentWrapper {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AppTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = signUpEmail,
                            modifier = Modifier.fillMaxWidth()
                        )

                        AppTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = password,
                            modifier = Modifier.fillMaxWidth()
                        )

                        AppFilledButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                // kalau email dan password tidak kosong, maka buat akun
                                if (email.isNotEmpty() && password.isNotEmpty()) {
                                    auth.createUserWithEmailAndPassword(email, password)
                                        // kalau berhasil, arahkan ke home
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) navController.navigate("home") {
                                                popUpTo("register") { inclusive = true }
                                            }
                                        }
                                }
                            }
                        ) {
                            Text(signUpButton)
                        }
                    }
                }
            }

            AppTextButton(onClick = { navController.navigate("login") }) {
                Text(signUpHaveAccount)
            }
        }
    }
}