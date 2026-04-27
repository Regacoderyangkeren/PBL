package com.example.pbl.ui.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun shakingAnimation(): Pair<Float, () -> Unit> {
    val shake = remember { Animatable(0f) }
    var trigger by remember { mutableLongStateOf(0L) }

    // got this stuff from stack overflow (to whoever made this, tyfm :pray:)
    LaunchedEffect(trigger) {
        if (trigger != 0L) {
            for (i in 0..10) {
                when (i % 2) {
                    0 -> shake.animateTo(5f, spring(stiffness = 100_000f))
                    else -> shake.animateTo(-5f, spring(stiffness = 100_000f))
                }
            }
            shake.animateTo(0f)
        }
    }

    return shake.value to { trigger = System.currentTimeMillis() }
}