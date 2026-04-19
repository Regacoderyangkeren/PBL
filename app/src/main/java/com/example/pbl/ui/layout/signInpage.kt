package com.example.pbl.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pbl.data.viewmodel.AuthEvent
import com.example.pbl.data.viewmodel.AuthState
import com.example.pbl.data.viewmodel.AuthViewModel
import com.example.pbl.ui.components.*
import com.example.pbl.ui.uihelpers.CardContentWrapper
import com.example.pbl.ui.theme.*
import com.example.pbl.ui.uihelpers.SignInFormState
import com.example.pbl.ui.uihelpers.ValidatedTextField
import com.example.pbl.ui.uihelpers.ValidationType
import com.example.pbl.ui.uihelpers.UiError
import com.example.pbl.ui.uihelpers.validateAndUpdate
import com.example.pbl.ui.uihelpers.isValidEmail

@Composable
fun SignInPage(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var formState by remember { mutableStateOf(SignInFormState()) }
    val authState by authViewModel.authState.collectAsState()

    // one-time navigation event
    LaunchedEffect(Unit) {
        authViewModel.authEvent.collect { event ->
            when (event) {
                is AuthEvent.NavigateToHome -> navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
                is AuthEvent.ShowError -> { /* ditunjukin di authstate */ }
            }
        }
    }

    // map backend UiError untuk error field-level
    LaunchedEffect(authState) {
        if (authState is AuthState.Error) {
            when (val err = (authState as AuthState.Error).error) {
                is UiError.InvalidCredentials ->
                    formState = formState.copy(
                        password = formState.password.copy(error = errorLoginPassword)
                    )
                is UiError.Message ->
                    if (err.text.contains("email", ignoreCase = true))
                        formState = formState.copy(email = formState.email.copy(error = err.text))
                is UiError.NetworkError -> { /* ditunjukin dibawah */ }
            }
        }
    }

    val isLoading = authState is AuthState.Loading

    Surface(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
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
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                CardContentWrapper {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            signIn,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 24.dp, bottom = 4.dp)
                        )
                        Text(
                            signInSubtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // email
                        ValidatedTextField(
                            value = formState.email.text,
                            onValueChange = {
                                formState = formState.copy(
                                    email = formState.email.validateAndUpdate(it, ValidationType.EMAIL)
                                )
                                authViewModel.resetAuthState()
                            },
                            placeholder = signUpEmail,
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !isLoading,
                            validationType = ValidationType.EMAIL
                        )
                        formState.email.error?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(start = 4.dp).align(Alignment.Start)
                            )
                        }

                        // password (general)
                        ValidatedTextField(
                            value = formState.password.text,
                            onValueChange = {
                                formState = formState.copy(
                                    password = formState.password.validateAndUpdate(it, ValidationType.GENERAL)
                                )
                                authViewModel.resetAuthState()
                            },
                            placeholder = userPassword,
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !isLoading,
                            validationType = ValidationType.GENERAL,
                            visualTransformation = PasswordVisualTransformation()
                        )
                        formState.password.error?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(start = 4.dp).align(Alignment.Start)
                            )
                        }

                        // network error
                        if (authState is AuthState.Error &&
                            (authState as AuthState.Error).error is UiError.NetworkError
                        ) {
                            Text(
                                text = "Network error. Please try again.",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.align(Alignment.Start)
                            )
                        }

                        // progress bar (garis)
                        if (isLoading) {
                            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        }

                        AppFilledButton(
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !isLoading,
                            onClick = {
                                when {
                                    formState.email.text.isEmpty() -> {
                                        formState = formState.copy(
                                            email = formState.email.copy(error = errorInputBlank)
                                        )
                                    }
                                    !isValidEmail(formState.email.text) -> {
                                        formState = formState.copy(
                                            email = formState.email.copy(error = errorLoginInvalidEmail)
                                        )
                                    }
                                    formState.password.text.isEmpty() -> {
                                        formState = formState.copy(
                                            password = formState.password.copy(error = errorInputBlank)
                                        )
                                    }
                                    else -> authViewModel.login(
                                        formState.email.text,
                                        formState.password.text
                                    )
                                }
                            }
                        ) {
                            // loading indicator
                            if (isLoading) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(18.dp),
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        strokeWidth = 2.dp
                                    )
                                    Text("Signing in...")
                                }
                            } else {
                                Text(signInButton)
                            }
                        }

                        AppTextButton(
                            onClick = { navController.navigate("register") },
                            contentColor = MaterialTheme.colorScheme.primary
                        ) {
                            Text(signInNoAccount)
                        }
                    }
                }
            }
        }
    }
}