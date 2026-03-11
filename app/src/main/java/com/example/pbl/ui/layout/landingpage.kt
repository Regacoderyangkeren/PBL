package com.example.pbl.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pbl.ui.components.*
import com.example.pbl.ui.theme.*
@Composable
fun LandingPage(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                LandingWaveBG()

                Column(
                    modifier = Modifier.padding(top = 125.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Landingpageimg()

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = titleDrop,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = subtitleDrop,
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(30.dp))

            AppFilledButton(
                text = landingPagebtn1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 12.dp)
            ) {
                navController.navigate("learn")
            }

            AppOutlinedButton(
                text = landingPagebtn2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                navController.navigate("login")
            }
        }
    }
}