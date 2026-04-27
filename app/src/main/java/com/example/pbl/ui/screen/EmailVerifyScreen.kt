package com.example.pbl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pbl.ui.animation.pulsingAnimation
import com.example.pbl.ui.components.*
import com.example.pbl.ui.theme.*
import com.example.pbl.ui.viewmodel.EmailVerifyEvent
import com.example.pbl.ui.viewmodel.EmailVerifyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailVerifyPage(
    navController: NavController,
    emailVerifyViewModel: EmailVerifyViewModel = viewModel()
) {
    val resendCooldown by emailVerifyViewModel.resendCooldown.collectAsState()
    val isResending by emailVerifyViewModel.isResending.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        emailVerifyViewModel.event.collect { event ->
            when (event) {
                is EmailVerifyEvent.NavigateToHome -> {
                    navController.navigate("home") {
                        popUpTo("verify") { inclusive = true }
                        popUpTo("register") { inclusive = true }
                    }
                }
                is EmailVerifyEvent.VerificationTimeout -> {
                    navController.navigate("register") {
                        popUpTo("verify") { inclusive = true }
                    }
                    snackbarHostState.showSnackbar(errorVerifyTimeout)
                }
            }
        }
    }

    val pulseAlpha = pulsingAnimation()

    AppScaffold(
        navController = navController,
        snackbarHost  = { AppSnackbarHost(snackbarHostState) },
        navigationIcon = {
            IconButton(onClick = {
                emailVerifyViewModel.cancelAndDeleteAccount()
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Loginpageimg()

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = emailVerifyTitle,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = emailVerifySubtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(56.dp).alpha(pulseAlpha),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 4.dp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = emailVerifyWaiting,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.alpha(pulseAlpha)
            )

            Spacer(modifier = Modifier.height(48.dp))

            AppFilledButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = resendCooldown == 0 && !isResending,
                onClick = { emailVerifyViewModel.resendVerificationEmail() }
            ) {
                when {
                    isResending -> Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                        Text(emailVerifyResending)
                    }
                    resendCooldown > 0 -> Text("$emailVerifyResendTimer${resendCooldown}s")
                    else -> Text(emailVerifyResend)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            AppTextButton(
                onClick = {
                    emailVerifyViewModel.cancelAndDeleteAccount()
                    navController.navigate("login") {
                        popUpTo("verify") { inclusive = true }
                        popUpTo("register") { inclusive = true }
                    }
                }
            ) {
                Text(emailVerifyWrongEmail)
            }
        }
    }
}