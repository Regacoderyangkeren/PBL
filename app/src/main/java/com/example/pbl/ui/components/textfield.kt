package com.example.pbl.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pbl.ui.animation.appColorTextFieldAnim

import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    focusedContainerColor: Color = MaterialTheme.colorScheme.primary,
    focusedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    placeholder: String = "",
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val isFocused by interactionSource.collectIsFocusedAsState()

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.heightIn(min = 48.dp),
        enabled = enabled,
        interactionSource = interactionSource,
        visualTransformation = visualTransformation,
        placeholder = {
            Text(
                placeholder,
                style = MaterialTheme.typography.bodyMedium)
                      },
        colors = appColorTextFieldAnim(
            isSelected = isFocused,
            selectedContainer = focusedContainerColor,
            defaultContainer = containerColor,
            selectedContent = focusedContentColor,
            defaultContent = contentColor
        ),
        shape = MaterialTheme.shapes.medium,
        singleLine = singleLine,
        textStyle = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    focusedContainerColor: Color = MaterialTheme.colorScheme.primary,
    focusedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    placeholder: String = "",
    singleLine: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.heightIn(min = 48.dp),
        interactionSource = interactionSource,
        placeholder = {
            Text(
                placeholder,
                style = MaterialTheme.typography.bodyMedium)
                      },
        colors = appColorTextFieldAnim(
            isSelected = isFocused,
            selectedContainer = focusedContainerColor,
            defaultContainer = containerColor,
            selectedContent = focusedContentColor,
            defaultContent = contentColor
        ),
        shape = MaterialTheme.shapes.medium,
        singleLine = singleLine,
        textStyle = MaterialTheme.typography.bodyMedium
    )
}