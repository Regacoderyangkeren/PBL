package com.example.pbl.ui.theme

// fonts
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// from res
import com.example.pbl.R

// typography
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

// use them fonts
val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

// smth to remember, there is display, title, label, body, and headline + large/medium/small
val Typography = Typography (
    displayLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp
    ),
    displayMedium = TextStyle (
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp
    ),
    displaySmall = TextStyle (
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),

)

