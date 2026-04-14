package com.example.pbl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.pbl.R

@Composable
fun Landingpageimg() {
    val testGambar = painterResource(id = R.drawable.test_gambar)

    // ada painter, content desc, modifier, content scale, alignment, dan color filter
    Image(
        painter = testGambar,
        contentDescription = "test gambar",
        modifier = Modifier
            .size(150.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun LandingWaveBG() {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val Wave = if (darkTheme) {
        painterResource(id = R.drawable.wave_darkmode)
    } else {
        painterResource(id = R.drawable.wave_lightmode)
    }

    Image(
        painter = Wave,
        contentDescription = "wavy wavy so it look cool or smth",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun Loginpageimg() {
    val testGambar = painterResource(id = R.drawable.test_gambar)

    Image(
        painter = testGambar,
        contentDescription = "test gambar",
        modifier = Modifier.size(150.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun Personsvg() {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val Person = if (darkTheme) {
        painterResource(id = R.drawable.person_darkmode)
    } else {
        painterResource(id = R.drawable.person_lightmode)
    }
}

@Composable
fun Locksvg() {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val Lock = if (darkTheme) {
        painterResource(id = R.drawable.lock_darkmode)
    } else {
        painterResource(id = R.drawable.lock_lightmode)
    }
}

@Composable
fun Mailsvg() {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val Mail = if (darkTheme) {
        painterResource(id = R.drawable.mail_darkmode)
    } else {
        painterResource(id = R.drawable.mail_lightmode)
    }
}