package com.example.pbl.ui.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

@Composable
fun appButtonAnim(
    isSelected: Boolean,
    defaultContainer: Color,
    selectedContainer: Color,
    defaultContent: Color,
    selectedContent: Color
): ButtonColors {
    val container by animateColorAsState(
        targetValue = if (isSelected) selectedContainer else defaultContainer,
        animationSpec = tween(durationMillis = 200)
    )

    val content by animateColorAsState(
        targetValue = if (isSelected) selectedContent else defaultContent,
        animationSpec = tween(durationMillis = 200)
    )

    return ButtonDefaults.buttonColors(
        containerColor = container,
        contentColor = content
    )
}

@Composable
fun appTextFieldAnim(
    isSelected: Boolean,
    defaultContainer: Color,
    selectedContainer: Color,
    defaultContent: Color,
    selectedContent: Color
): TextFieldColors {
    val container by animateColorAsState(
        targetValue = if (isSelected) selectedContainer else defaultContainer,
        animationSpec = tween(durationMillis = 200)
    )

    val content by animateColorAsState(
        targetValue = if (isSelected) selectedContent else defaultContent,
        animationSpec = tween(durationMillis = 200)
    )

    return TextFieldDefaults.colors(
        focusedContainerColor = container,
        unfocusedContainerColor = container,
        focusedTextColor = content,
        unfocusedTextColor = content
    )
}