package com.example.pbl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pbl.ui.theme.*

@Composable
fun TermsAndConditionsPage(navController: NavController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = termsLastUpdated,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        TermsSection(title = termsIntroTitle, body = termsIntroBody)
        TermsSection(title = termsAccountTitle, body = termsAccountBody)
        TermsSection(title = termsUseTitle, body = termsUseBody)
        TermsSection(title = termsPrivacyTitle, body = termsPrivacyBody)
        TermsSection(title = termsIpTitle, body = termsIpBody)
        TermsSection(title = termsTerminationTitle, body = termsTerminationBody)
        TermsSection(title = termsLiabilityTitle, body = termsLiabilityBody)
        TermsSection(title = termsChangesTitle, body = termsChangesBody)
        TermsSection(title = termsContactTitle, body = termsContactBody)

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun TermsSection(title: String, body: String) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
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