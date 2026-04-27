package com.example.pbl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pbl.ui.viewmodel.AuthViewModel
import com.example.pbl.ui.components.*
import com.example.pbl.ui.theme.*
import com.example.pbl.ui.util.helper.ValidatedTextField
import com.example.pbl.ui.util.helper.ValidationType
import com.example.pbl.ui.util.helper.validateAndUpdate
import com.example.pbl.ui.util.helper.FieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordPage(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    var emailField by remember { mutableStateOf(FieldState()) }
    var isLoading by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(isSuccess) {
        if (isSuccess) snackbarHostState.showSnackbar(forgotPasswordSuccess)
    }

    AppScaffold(
        navController = navController,
        snackbarHost = { AppSnackbarHost(snackbarHostState) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    ) { scaffoldPadding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(scaffoldPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Loginpageimg()

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    forgotPasswordTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    forgotPasswordSubtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(32.dp))

                // email field
                ValidatedTextField(
                    value = emailField.text,
                    onValueChange = {
                        emailField = emailField.validateAndUpdate(it, ValidationType.EMAIL)
                        errorMessage = null
                        isSuccess = false
                    },
                    placeholder = forgotPasswordEmail,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading,
                    validationType = ValidationType.EMAIL,
                    isError = errorMessage != null
                )

                errorMessage?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(start = 4.dp).align(Alignment.Start)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (isLoading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(8.dp))
                }

                AppFilledButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = emailField.isValid && !isLoading && !isSuccess,
                    onClick = {
                        isLoading = true
                        authViewModel.resetPassword(
                            email = emailField.text,
                            onSuccess = {
                                isLoading = false
                                isSuccess = true
                            },
                            onFailure = { error ->
                                isLoading = false
                                errorMessage = when (error) {
                                    errorCodeUserNotFound -> errorLoginEmail
                                    errorCodeInvalidEmail -> errorSignUpEmail
                                    else -> errorUnknown
                                }
                            }
                        )
                    }
                ) {
                    Text(forgotPasswordButton)
                }

                AppTextButton(
                    onClick = { navController.navigate("login") {
                        popUpTo("forgotpassword") { inclusive = true }
                    }},
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    Text(forgotPasswordBackToLogin)
                }
            }
        }
    }
}