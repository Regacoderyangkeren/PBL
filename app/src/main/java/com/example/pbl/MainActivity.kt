package com.example.pbl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pbl.ui.navigations.AppNavigation
import com.example.pbl.ui.theme.PBLTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PBLTheme {
                AppNavigation()
            }
        }
    }
}