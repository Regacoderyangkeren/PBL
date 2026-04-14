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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pbl.ui.components.*
import com.example.pbl.ui.uihelpers.CardContentWrapper
import com.example.pbl.ui.theme.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignInPage(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Loginpageimg()

            Spacer(modifier = Modifier.height(50.dp))

            AppStaticFilledCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                CardContentWrapper {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            signIn,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .padding(bottom = 16.dp, top = 32.dp)
                        )

                        AppTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = "Email",
                            modifier = Modifier.fillMaxWidth()
                        )

                        AppTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = "password",
                            modifier = Modifier.fillMaxWidth()
                        )

                        AppFilledButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                // kalau email dan password tidak kosong, maka masuk
                                if (email.isNotEmpty() && password.isNotEmpty()) {
                                    auth.signInWithEmailAndPassword(email, password)
                                        // kalau berhasil, arahkan ke home
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) navController.navigate("home") {
                                                popUpTo("login") { inclusive = true }
                                            }
                                        }
                                }
                            }
                        ) {
                            Text(signInButton)
                        }

                        AppTextButton(onClick = { navController.navigate("register") }) {
                            Text(signInNoAccount)
                        }
                    }
                }
            }
        }
    }
}