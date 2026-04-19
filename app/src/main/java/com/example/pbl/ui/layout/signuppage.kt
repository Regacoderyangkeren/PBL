package com.example.pbl.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.pbl.ui.theme.*
import com.example.pbl.ui.uihelpers.*

@Composable
fun SignUpPage(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var formState by remember { mutableStateOf(SignUpFormState()) }
    var hasValidated by remember { mutableStateOf(false) }
    val authState by authViewModel.authState.collectAsState()

    // one-time navigation events
    LaunchedEffect(Unit) {
        authViewModel.authEvent.collect { event ->
            when (event) {
                is AuthEvent.NavigateToHome -> navController.navigate("home") {
                    popUpTo("register") { inclusive = true }
                }
                is AuthEvent.ShowError -> { /* shown via authState */ }
            }
        }
    }

    // hint rows, recomputed on each recompose
    fun ValidationHint.toTriple() = Triple(hintText, errorText, isPassed)

    val pwRules = getPasswordRules(formState.password.text).map { it.toTriple() }
    val firstNameHint = getRequiredFieldHint(formState.firstName.text, signUpFirstNameHint, signUpFirstNameError).map { it.toTriple() }
    val lastNameHint = getRequiredFieldHint(formState.lastName.text, signUpLastNameHint, signUpLastNameError).map { it.toTriple() }
    val aliasHint = getAliasHint(formState.alias.text).map { it.toTriple() }
    val emailHint = getEmailHint(formState.email.text).map { it.toTriple() }

    val isLoading = authState is AuthState.Loading

    Surface(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Loginpageimg()
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                signUpTitle,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                signUpSubtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // first name
            ValidatedTextField(
                value = formState.firstName.text,
                onValueChange = {
                    formState = formState.copy(
                        firstName = formState.firstName.validateAndUpdate(it, ValidationType.ALPHANUMERIC)
                    )
                },
                placeholder = signUpFirstName,
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                validationType = ValidationType.ALPHANUMERIC,
                hasBeenFocused = formState.firstName.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(firstName = formState.firstName.copy(hasBeenFocused = it)) },
                hintRows = firstNameHint
            )

            Spacer(modifier = Modifier.height(16.dp))

            // last name
            ValidatedTextField(
                value = formState.lastName.text,
                onValueChange = {
                    formState = formState.copy(
                        lastName = formState.lastName.validateAndUpdate(it, ValidationType.ALPHANUMERIC)
                    )
                },
                placeholder = signUpLastName,
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                validationType = ValidationType.ALPHANUMERIC,
                hasBeenFocused = formState.lastName.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(lastName = formState.lastName.copy(hasBeenFocused = it)) },
                hintRows = lastNameHint
            )

            Spacer(modifier = Modifier.height(16.dp))

            // alias
            ValidatedTextField(
                value = formState.alias.text,
                onValueChange = {
                    formState = formState.copy(
                        alias = formState.alias.validateAndUpdate(it, ValidationType.USERNAME)
                    )
                },
                placeholder = signUpAlias,
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                validationType = ValidationType.USERNAME,
                hasBeenFocused = formState.alias.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(alias = formState.alias.copy(hasBeenFocused = it)) },
                hintRows = aliasHint
            )

            Spacer(modifier = Modifier.height(16.dp))

            // email
            ValidatedTextField(
                value = formState.email.text,
                onValueChange = {
                    formState = formState.copy(
                        email = formState.email.validateAndUpdate(it, ValidationType.EMAIL)
                    )
                },
                placeholder = signUpEmail,
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                validationType = ValidationType.EMAIL,
                hasBeenFocused = formState.email.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(email = formState.email.copy(hasBeenFocused = it)) },
                hintRows = emailHint
            )

            Spacer(modifier = Modifier.height(16.dp))

            // password with per-rule hint rows
            ValidatedTextField(
                value = formState.password.text,
                onValueChange = {
                    formState = formState.copy(
                        password = formState.password.validateAndUpdate(it, ValidationType.PASSWORD)
                    )
                },
                placeholder = userPassword,
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                validationType = ValidationType.PASSWORD,
                visualTransformation = PasswordVisualTransformation(),
                hasBeenFocused = formState.password.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(password = formState.password.copy(hasBeenFocused = it)) },
                hintRows = pwRules
            )

            Spacer(modifier = Modifier.height(32.dp))

            // progress bar (garis)
            if (isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
            }

            // backend error display
            if (authState is AuthState.Error) {
                val msg = when (val err = (authState as AuthState.Error).error) {
                    is UiError.NetworkError -> "Network error. Please try again."
                    is UiError.InvalidCredentials -> "Registration failed. Please try again."
                    is UiError.Message -> err.text
                }
                Text(
                    text = msg,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            AppFilledButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                onClick = {
                    hasValidated = true
                    if (formState.isValid) {
                        authViewModel.register(
                            email = formState.email.text,
                            password = formState.password.text,
                            firstName = formState.firstName.text,
                            lastName = formState.lastName.text,
                            alias = formState.alias.text
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
                        Text("Creating Account...")
                    }
                } else {
                    Text(signUpButton)
                }
            }

            AppTextButton(
                onClick = { navController.navigate("login") },
                contentColor = MaterialTheme.colorScheme.primary,
                enabled = !isLoading
            ) {
                Text(signUpHaveAccount)
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}