package com.example.pbl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pbl.ui.components.AppScaffold
import com.example.pbl.ui.theme.*

@Composable
fun TermsAndConditionsPage(navController: NavController) {
    AppScaffold(
        navController = navController,
        title = termsTitle,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // last updated
            Text(
                text = termsLastUpdated,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // 1. Introduction
            TermsSection(title = termsIntroTitle, body = termsIntroBody)

            // 2. Account Registration
            TermsSection(title = termsAccountTitle, body = termsAccountBody)

            // 3. Acceptable Use
            TermsSection(title = termsUseTitle, body = termsUseBody)

            // 4. Privacy
            TermsSection(title = termsPrivacyTitle, body = termsPrivacyBody)

            // 5. Intellectual Property
            TermsSection(title = termsIpTitle, body = termsIpBody)

            // 6. Termination
            TermsSection(title = termsTerminationTitle, body = termsTerminationBody)

            // 7. Limitation of Liability
            TermsSection(title = termsLiabilityTitle, body = termsLiabilityBody)

            // 8. Changes to Terms
            TermsSection(title = termsChangesTitle, body = termsChangesBody)

            // 9. Contact
            TermsSection(title = termsContactTitle, body = termsContactBody)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TermsSection(
    title: String,
    body: String)
{
    Column(verticalArrangement = Arrangement.spacedBy(6.dp))
    {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = body,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}