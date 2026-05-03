package com.example.pbl.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pbl.ui.viewmodel.AuthState
import com.example.pbl.ui.viewmodel.AuthEvent
import com.example.pbl.ui.viewmodel.AuthViewModel
import com.example.pbl.ui.components.*
import com.example.pbl.ui.theme.*
import com.example.pbl.ui.form.*
import com.example.pbl.ui.util.uihelper.FieldState
import com.example.pbl.ui.util.uihelper.ValidatedTextField
import com.example.pbl.ui.util.uihelper.ValidationHint
import com.example.pbl.ui.util.uihelper.ValidationType
import com.example.pbl.ui.util.uihelper.getAliasHint
import com.example.pbl.ui.util.uihelper.getConfirmPasswordHint
import com.example.pbl.ui.util.uihelper.getEmailHint
import com.example.pbl.ui.util.uihelper.getPasswordRules
import com.example.pbl.ui.util.uihelper.getRequiredFieldHint
import com.example.pbl.ui.util.uihelper.validateAndUpdate

@Composable
fun SignUpPage(
    navController: NavController,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    authViewModel: AuthViewModel = viewModel()
) {
    var formState by remember { mutableStateOf(SignUpFormState()) }
    var hasValidated by remember { mutableStateOf(false) }
    var shakeFields by remember { mutableStateOf(setOf<String>()) }
    var hasNavigated by remember { mutableStateOf(false) }
    val authState by authViewModel.authState.collectAsState()

    // handle navigation dan error dari authViewModel
    LaunchedEffect(Unit) {
        authViewModel.authEvent.collect { event ->
            if (hasNavigated) return@collect
            when (event) {
                is AuthEvent.NavigateToEmailVerify -> {
                    hasNavigated = true
                    authViewModel.resetState()
                    navController.navigate("verify") {
                        popUpTo("register") { inclusive = true }
                    }
                }
                is AuthEvent.LoadingTimedOut -> {
                    snackbarHostState.showSnackbar(errorLoadingTimeout)
                }
                else -> {}
            }
        }
    }

    // reset hasNavigated jika terjadi error baru
    LaunchedEffect(authState) {
        if (authState is AuthState.Error) hasNavigated = false
    }

    // disable input dan tombol saat loading
    val isLoading = authState is AuthState.Loading

    fun ValidationHint.toTriple() = Triple(hintText, errorText, isPassed)

    // generate hint rows untuk setiap field
    val pwRules = getPasswordRules(
        formState.password.text).map { it.toTriple() }
    val firstNameHint = getRequiredFieldHint(
        formState.firstName.text,
        signUpFirstNameHint,
        signUpFirstNameError).map { it.toTriple() }
    val lastNameHint = getRequiredFieldHint(
        formState.lastName.text,
        signUpLastNameHint,
        signUpLastNameError).map { it.toTriple() }
    val aliasHint = getAliasHint(
        formState.alias.text).map { it.toTriple() }
    val emailHint = getEmailHint(
        formState.email.text).map { it.toTriple() }
    val confirmPasswordHint = getConfirmPasswordHint(
        formState.password.text,
        formState.confirmPassword.text).map { it.toTriple() }

    Surface(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
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

            Spacer(modifier = Modifier.height(32.dp))

            // firstName field
            ValidatedTextField(
                value = formState.firstName.text,
                onValueChange = { formState = formState.copy(
                    firstName = formState.firstName.validateAndUpdate(
                        it,
                        ValidationType.ALPHANUMERIC)); shakeFields = shakeFields - "firstName" },
                placeholder = signUpFirstName,
                modifier = Modifier.fillMaxWidth(),
                validationType = ValidationType.ALPHANUMERIC,
                hasBeenFocused = formState.firstName.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(
                    firstName = formState.firstName.copy(hasBeenFocused = it)) },
                hintRows = firstNameHint, isError = hasValidated && !formState.firstName.isValid,
                shakeTriggered = "firstName" in shakeFields,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            // lastName field
            ValidatedTextField(
                value = formState.lastName.text,
                onValueChange = { formState = formState.copy(
                    lastName = formState.lastName.validateAndUpdate(
                        it,
                        ValidationType.ALPHANUMERIC)); shakeFields = shakeFields - "lastName" },
                placeholder = signUpLastName,
                modifier = Modifier.fillMaxWidth(),
                validationType = ValidationType.ALPHANUMERIC,
                hasBeenFocused = formState.lastName.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(
                    lastName = formState.lastName.copy(hasBeenFocused = it)) },
                hintRows = lastNameHint,
                isError = hasValidated && !formState.lastName.isValid,
                shakeTriggered = "lastName" in shakeFields,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            // alias field
            ValidatedTextField(
                value = formState.alias.text,
                onValueChange = { formState = formState.copy(
                    alias = formState.alias.validateAndUpdate(
                        it,
                        ValidationType.USERNAME)); shakeFields = shakeFields - "alias" },
                placeholder = signUpAlias,
                modifier = Modifier.fillMaxWidth(),
                validationType = ValidationType.USERNAME,
                hasBeenFocused = formState.alias.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(
                    alias = formState.alias.copy(hasBeenFocused = it)) },
                hintRows = aliasHint,
                isError = hasValidated && !formState.alias.isValid,
                shakeTriggered = "alias" in shakeFields,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            // email field
            ValidatedTextField(
                value = formState.email.text,
                onValueChange = { formState = formState.copy(
                    email = formState.email.validateAndUpdate(
                        it,
                        ValidationType.EMAIL)); shakeFields = shakeFields - "email" },
                placeholder = signUpEmail,
                modifier = Modifier.fillMaxWidth(),
                validationType = ValidationType.EMAIL,
                hasBeenFocused = formState.email.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(
                    email = formState.email.copy(hasBeenFocused = it)) },
                hintRows = emailHint,
                isError = hasValidated && !formState.email.isValid,
                shakeTriggered = "email" in shakeFields, enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            // password field
            ValidatedTextField(
                value = formState.password.text,
                onValueChange = { formState = formState.copy(
                    password = formState.password.validateAndUpdate(
                        it,
                        ValidationType.PASSWORD),
                    confirmPassword = formState.confirmPassword.copy(
                        error = if (
                            formState.confirmPassword.text.isNotEmpty() &&
                            formState.confirmPassword.text != it
                            ) errorConfirmPasswordMismatch else null)); shakeFields = shakeFields - "password" },
                placeholder = userPassword,
                modifier = Modifier.fillMaxWidth(),
                validationType = ValidationType.PASSWORD,
                isPasswordField = true,
                hasBeenFocused = formState.password.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(
                    password = formState.password.copy(hasBeenFocused = it)) },
                hintRows = pwRules,
                isError = hasValidated && !formState.password.isValid,
                shakeTriggered = "password" in shakeFields,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            // confirm password field
            ValidatedTextField(
                value = formState.confirmPassword.text,
                onValueChange = { val matches = it == formState.password.text; formState = formState.copy(
                    confirmPassword = FieldState(
                        text = it, error = if (it.isNotEmpty() && !matches
                            ) errorConfirmPasswordMismatch else null,
                        hasBeenFocused = formState.confirmPassword.hasBeenFocused)); shakeFields = shakeFields - "confirmPassword" },
                placeholder = signUpConfirmPassword, modifier = Modifier.fillMaxWidth(),
                validationType = ValidationType.GENERAL,
                isPasswordField = true,
                hasBeenFocused = formState.confirmPassword.hasBeenFocused,
                shouldValidate = hasValidated,
                onFocusChanged = { formState = formState.copy(
                    confirmPassword = formState.confirmPassword.copy(
                        hasBeenFocused = it)) },
                hintRows = confirmPasswordHint,
                isError = hasValidated && !formState.confirmPassword.isValid,
                shakeTriggered = "confirmPassword" in shakeFields,
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            // terms and conditions
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = formState.agreedToTerms,
                    onCheckedChange = { formState = formState.copy(agreedToTerms = it) })
                Row {
                    Text(
                        text = signUpTermsPrefix,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = signUpTermsLink,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { navController.navigate("terms") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (authState is AuthState.Error) {
                val msg = when (val err = (authState as AuthState.Error).error) {
                    is UiError.NetworkError -> errorNetworkGeneral
                    is UiError.InvalidCredentials -> errorUnknown
                    is UiError.Message -> err.text
                }
                Text(
                    text = msg,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            if (isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(16.dp))
            }

            // sign up button
            AppFilledButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = formState.agreedToTerms && !isLoading,
                onClick = {
                    hasValidated = true
                    if (formState.isValid) {
                        authViewModel.register(formState.toRegisterParams())
                    } else {
                        val invalidFields = mutableSetOf<String>()
                        if (!formState.firstName.isValid) invalidFields += "firstName"
                        if (!formState.lastName.isValid) invalidFields += "lastName"
                        if (!formState.alias.isValid) invalidFields += "alias"
                        if (!formState.email.isValid) invalidFields += "email"
                        if (!formState.password.isValid) invalidFields += "password"
                        if (!formState.confirmPassword.isValid) invalidFields += "confirmPassword"
                        shakeFields = invalidFields
                    }
                }
            ) {
                if (isLoading) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )

                        Text(loadingRegistering)
                    }
                } else {
                    Text(signUpButton)
                }
            }

            // navigasi ke login page
            AppTextButton(
                onClick = { navController.navigate("login") },
                contentColor = MaterialTheme.colorScheme.primary) {
                Text(signUpHaveAccount)
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}