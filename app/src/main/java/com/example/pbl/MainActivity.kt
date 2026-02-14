package com.example.pbl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.pbl.ui.theme.PBLTheme
import com.example.pbl.ui.components.Scaffolds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PBLTheme {
                Scaffolds()
                Greeting("Android")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello rega!",
         color = MaterialTheme.colorScheme.primary)
}

@Preview(showBackground = true, name = "Main")
@Composable
fun DefaultPreview() {
    PBLTheme {
        Greeting("Android")
    }
}
