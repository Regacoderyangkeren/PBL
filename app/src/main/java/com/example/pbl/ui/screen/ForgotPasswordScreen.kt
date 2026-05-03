package com.example.pbl.ui.screen

import androidx.compose.foundation.layout.*
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
import com.example.pbl.ui.util.uihelper.ValidatedTextField
import com.example.pbl.ui.util.uihelper.ValidationType
import com.example.pbl.ui.util.uihelper.validateAndUpdate
import com.example.pbl.ui.util.uihelper.FieldState

@Composable
fun ForgotPasswordPage(
    navController: NavController,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    authViewModel: AuthViewModel = viewModel()
) {
    var emailField by remember { mutableStateOf(FieldState()) }
    var isLoading by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Tampilkan snackbar saat reset password berhasil
    LaunchedEffect(isSuccess) {
        if (isSuccess) snackbarHostState.showSnackbar(forgotPasswordSuccess)
    }

    Surface(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
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
                    emailField = emailField.validateAndUpdate(
                        it,
                        ValidationType.EMAIL)
                    errorMessage = null
                    isSuccess = false
                },
                placeholder = forgotPasswordEmail,
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                validationType = ValidationType.EMAIL,
                isError = errorMessage != null
            )

            // tampilkan error jika ada
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

            // reset password button
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

            // balik ke login
            AppTextButton(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("forgotpassword") { inclusive = true }
                    }
                },
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Text(forgotPasswordBackToLogin)
            }
        }
    }
}