package com.example.pbl.ui.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

@Composable
fun appColorButtonAnim(
    isSelected: Boolean,
    defaultContainer: Color,
    selectedContainer: Color,
    defaultContent: Color,
    selectedContent: Color
): ButtonColors {
    val container by animateColorAsState(
        targetValue = if (isSelected) selectedContainer else defaultContainer,
        animationSpec = tween(durationMillis = 200),
        label = "buttonContainer"
    )

    val content by animateColorAsState(
        targetValue = if (isSelected) selectedContent else defaultContent,
        animationSpec = tween(durationMillis = 200),
        label = "buttonContent"
    )

    return ButtonDefaults.buttonColors(
        containerColor = container,
        contentColor = content
    )
}

@Composable
fun appColorTextFieldAnim(
    isSelected: Boolean,
    defaultContainer: Color,
    selectedContainer: Color,
    defaultContent: Color,
    selectedContent: Color
): TextFieldColors {
    val container by animateColorAsState(
        targetValue = if (isSelected) selectedContainer else defaultContainer,
        animationSpec = tween(durationMillis = 200),
        label = "textFieldContainer"
    )

    val content by animateColorAsState(
        targetValue = if (isSelected) selectedContent else defaultContent,
        animationSpec = tween(durationMillis = 200),
        label = "textFieldContent"
    )

    return TextFieldDefaults.colors(
        focusedContainerColor = container,
        unfocusedContainerColor = container,
        focusedTextColor = content,
        unfocusedTextColor = content,
        cursorColor = content,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = content,
        unfocusedLeadingIconColor = content,
        focusedTrailingIconColor = content,
        unfocusedTrailingIconColor = content,
        focusedLabelColor = content,
        unfocusedLabelColor = content,
        focusedPlaceholderColor = content.copy(alpha = 0.6f),
        unfocusedPlaceholderColor = content.copy(alpha = 0.6f)
    )
}

@Composable
fun appColorCardAnim(
    isSelected: Boolean,
    defaultContainer: Color,
    selectedContainer: Color,
    defaultContent: Color,
    selectedContent: Color
): CardColors {
    val container by animateColorAsState(
        targetValue = if (isSelected) selectedContainer else defaultContainer,
        animationSpec = tween(durationMillis = 200),
        label = "cardContainer"
    )

    val content by animateColorAsState(
        targetValue = if (isSelected) selectedContent else defaultContent,
        animationSpec = tween(durationMillis = 200),
        label = "cardContent"
    )

    return CardDefaults.cardColors(
        containerColor = container,
        contentColor = content
    )
}
