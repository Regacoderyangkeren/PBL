package com.example.pbl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pbl.ui.viewmodel.AuthEvent
import com.example.pbl.ui.viewmodel.AuthState
import com.example.pbl.ui.viewmodel.AuthViewModel
import com.example.pbl.ui.components.*
import com.example.pbl.ui.util.uihelper.CardContentWrapper
import com.example.pbl.ui.theme.*
import com.example.pbl.ui.form.SignInFormState
import com.example.pbl.ui.util.uihelper.ValidatedTextField
import com.example.pbl.ui.util.uihelper.ValidationType
import com.example.pbl.ui.util.uihelper.validateAndUpdate
import com.example.pbl.ui.form.toLoginParams
import com.example.pbl.ui.util.error.ErrorTarget
import com.example.pbl.ui.util.error.resolveError

@Composable
fun SignInPage(
    navController: NavController,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    authViewModel: AuthViewModel = viewModel()
) {
    var formState by remember { mutableStateOf(SignInFormState()) }
    val authState by authViewModel.authState.collectAsState()

    var backendEmailError by remember { mutableStateOf<String?>(null) }
    var backendPasswordError by remember { mutableStateOf<String?>(null) }
    var showNetworkError by remember { mutableStateOf(false) }

    // collect event dari viewmodel
    LaunchedEffect(Unit) {
        authViewModel.authEvent.collect { event ->
            when (event) {
                is AuthEvent.NavigateToHome -> {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                is AuthEvent.NavigateToEmailVerify -> {
                    authViewModel.resetState()
                    navController.navigate("verify") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                is AuthEvent.LoadingTimedOut -> {
                    snackbarHostState.showSnackbar(errorLoadingTimeout)
                }
                is AuthEvent.ShowError -> {
                    backendEmailError = null
                    backendPasswordError = null
                    showNetworkError = false

                    val resolved = resolveError(event.error)
                    when (resolved.target) {
                        is ErrorTarget.EmailField -> backendEmailError = resolved.message
                        is ErrorTarget.PasswordField -> backendPasswordError = resolved.message
                        is ErrorTarget.Inline -> showNetworkError = true
                        is ErrorTarget.Snackbar -> snackbarHostState.showSnackbar(resolved.message)
                    }
                }
            }
        }
    }

    // disable input dan tombol saat loading
    val isLoading = authState is AuthState.Loading

    Surface(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
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
                modifier = Modifier.fillMaxWidth().weight(1f),
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                CardContentWrapper {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
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

                        // email field
                        ValidatedTextField(
                            value = formState.email.text,
                            onValueChange = {
                                backendEmailError = null
                                formState = formState.copy(
                                    email = formState.email.validateAndUpdate(
                                        it,
                                        ValidationType.GENERAL)
                                )
                                authViewModel.resetState()
                            },
                            placeholder = signUpEmail,
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !isLoading,
                            validationType = ValidationType.EMAIL
                        )
                        val emailDisplayError = backendEmailError ?: formState.email.error
                        emailDisplayError?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(start = 4.dp).align(Alignment.Start)
                            )
                        }

                        // password field
                        ValidatedTextField(
                            value = formState.password.text,
                            onValueChange = {
                                backendPasswordError = null
                                formState = formState.copy(
                                    password = formState.password.validateAndUpdate(
                                        it,
                                        ValidationType.GENERAL)
                                )
                                authViewModel.resetState()
                            },
                            placeholder = userPassword,
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !isLoading,
                            validationType = ValidationType.GENERAL,
                            isPasswordField = true
                        )
                        val passwordDisplayError = backendPasswordError ?: formState.password.error
                        passwordDisplayError?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(start = 4.dp).align(Alignment.Start)
                            )
                        }

                        // forgot password dan register
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AppTextButton(
                                onClick = { navController.navigate("forgotpassword") },
                                contentColor = MaterialTheme.colorScheme.primary
                            ) {
                                Text(signInForgotPassword, style = MaterialTheme.typography.labelLarge)
                            }

                            AppTextButton(
                                onClick = { navController.navigate("register") },
                                contentColor = MaterialTheme.colorScheme.primary
                            ) {
                                Text(signInNoAccount, style = MaterialTheme.typography.labelLarge)
                            }
                        }

                        // network error
                        if (showNetworkError) {
                            Text(
                                text = errorNetworkGeneral,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.align(Alignment.Start)
                            )
                        }

                        if (isLoading) {
                            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        }

                        // login button
                        AppFilledButton(
                            modifier = Modifier.fillMaxWidth(),
                            enabled = formState.isValid && !isLoading,
                            onClick = { authViewModel.login(formState.toLoginParams()) }
                        ) {
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
                                    Text(loadingSigningIn)
                                }
                            } else {
                                Text(signInButton)
                            }
                        }
                    }
                }
            }
        }
    }
}