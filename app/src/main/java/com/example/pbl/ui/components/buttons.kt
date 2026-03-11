package com.example.pbl.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppFilledButton(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    focusedContainerColor: Color = MaterialTheme.colorScheme.primary,
    focusedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor =
                if (isFocused) focusedContainerColor
                else containerColor,
            contentColor =
                if (isFocused) focusedContentColor
                else contentColor
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
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    focusedContainerColor: Color = MaterialTheme.colorScheme.primary,
    focusedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor =
                if (isFocused) focusedContainerColor
                else containerColor,
            contentColor =
                if (isFocused) focusedContentColor
                else contentColor
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
    focusedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor =
                if (isFocused) focusedContentColor
                else contentColor
        ),
        border = BorderStroke(
            2.dp,
            contentColor
        )
    ) {
        Text(text = text)
    }
}

@Composable
fun AppElevatedButton(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    focusedContainerColor: Color = MaterialTheme.colorScheme.primary,
    focusedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor =
                if (isFocused) focusedContainerColor
                else containerColor,
            contentColor =
                if (isFocused) focusedContentColor
                else contentColor
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
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    focusedContainerColor: Color = MaterialTheme.colorScheme.primary,
    focusedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    TextButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor =
                if (isFocused) focusedContainerColor
                else containerColor,
            contentColor =
                if (isFocused) focusedContentColor
                else contentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = text)
    }
}
