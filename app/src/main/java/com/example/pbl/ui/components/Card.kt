package com.example.pbl.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.example.pbl.ui.animation.appColorCardAnim

@Composable
fun AppStaticFilledCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = MaterialTheme.shapes.medium,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = shape
    ) {
        content()
    }
}

@Composable
fun AppDynamicFilledCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Card(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        colors = appColorCardAnim(
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
fun AppStaticElevatedCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        content()
    }
}

@Composable
fun AppDynamicElevatedCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    ElevatedCard(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        colors = appColorCardAnim(
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
fun AppStaticOutlinedCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable () -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        content()
    }
}

@Composable
fun AppDynamicOutlinedCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    OutlinedCard(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        colors = appColorCardAnim(
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