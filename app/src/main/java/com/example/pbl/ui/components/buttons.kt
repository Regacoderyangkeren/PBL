package com.example.pbl.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pbl.ui.uihelpers.preventButtonSpamClicks
import com.example.pbl.ui.animation.appColorButtonAnim

@Composable
fun AppFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val safeClick = preventButtonSpamClicks(onClick = onClick)

    Button(
        onClick = safeClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = appColorButtonAnim(
            isSelected = isPressed,
            defaultContainer = containerColor,
            selectedContainer = pressedContainerColor,
            defaultContent = contentColor,
            selectedContent = pressedContentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        content()
    }
}

@Composable
fun AppTonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    pressedContainerColor: Color = MaterialTheme.colorScheme.secondary,
    pressedContentColor: Color = MaterialTheme.colorScheme.onSecondary,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val safeClick = preventButtonSpamClicks(onClick = onClick)

    FilledTonalButton(
        onClick = safeClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = appColorButtonAnim(
            isSelected = isPressed,
            defaultContainer = containerColor,
            selectedContainer = pressedContainerColor,
            defaultContent = contentColor,
            selectedContent = pressedContentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        content()
    }
}

@Composable
fun AppOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val safeClick = preventButtonSpamClicks(onClick = onClick)

    OutlinedButton(
        onClick = safeClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        shape = MaterialTheme.shapes.medium,
        colors = appColorButtonAnim(
            isSelected = isPressed,
            defaultContainer = Color.Transparent,
            selectedContainer = pressedContainerColor,
            defaultContent = contentColor,
            selectedContent = pressedContentColor
        ),
        border = BorderStroke(
            width = 2.dp,
            color = if (isPressed) MaterialTheme.colorScheme.primaryContainer else contentColor
        )
    ) {
        content()
    }
}

@Composable
fun AppElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val safeClick = preventButtonSpamClicks(onClick = onClick)

    ElevatedButton(
        onClick = safeClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = appColorButtonAnim(
            isSelected = isPressed,
            defaultContainer = containerColor,
            selectedContainer = pressedContainerColor,
            defaultContent = contentColor,
            selectedContent = pressedContentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        content()
    }
}

@Composable
fun AppTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val safeClick = preventButtonSpamClicks(onClick = onClick)

    TextButton(
        onClick = safeClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = appColorButtonAnim(
            isSelected = isPressed,
            defaultContainer = Color.Transparent,
            selectedContainer = pressedContainerColor,
            defaultContent = contentColor,
            selectedContent = pressedContentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        content()
    }
}