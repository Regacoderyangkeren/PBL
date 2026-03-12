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
import com.example.pbl.ui.animation.appButtonAnim

@Composable
fun AppFilledButton(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        colors = appButtonAnim(
            isSelected = isPressed,
            defaultContainer = containerColor,
            selectedContainer = pressedContainerColor,
            defaultContent = contentColor,
            selectedContent = pressedContentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = text)
    }
}

@Composable
fun AppTonalButton(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    pressedContainerColor: Color = MaterialTheme.colorScheme.secondary,
    pressedContentColor: Color = MaterialTheme.colorScheme.onSecondary,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        colors = appButtonAnim(
            isSelected = isPressed,
            defaultContainer = containerColor,
            selectedContainer = pressedContainerColor,
            defaultContent = contentColor,
            selectedContent = pressedContentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = text)
    }
}

@Composable
fun AppOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        shape = MaterialTheme.shapes.medium,
        colors = appButtonAnim(
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
        Text(text = text)
    }
}

@Composable
fun AppElevatedButton(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        colors = appButtonAnim(
            isSelected = isPressed,
            defaultContainer = containerColor,
            selectedContainer = pressedContainerColor,
            defaultContent = contentColor,
            selectedContent = pressedContentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = text)
    }
}

@Composable
fun AppTextButton(
    text: String,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    TextButton(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        colors = appButtonAnim(
            isSelected = isPressed,
            defaultContainer = Color.Transparent,
            selectedContainer = pressedContainerColor,
            defaultContent = contentColor,
            selectedContent = pressedContentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = text)
    }
}