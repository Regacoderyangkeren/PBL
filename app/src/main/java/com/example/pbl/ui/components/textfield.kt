package com.example.pbl.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pbl.ui.animation.appColorTextFieldAnim

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    focusedContainerColor: Color = MaterialTheme.colorScheme.primary,
    focusedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    placeholder: String = ""
) {
    val interactionSource = remember { MutableInteractionSource() }
    val IsFocused by interactionSource.collectIsFocusedAsState()

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        interactionSource = interactionSource,
        placeholder = { Text(placeholder) },
        colors = appColorTextFieldAnim(
            isSelected = IsFocused,
            selectedContainer = focusedContainerColor,
            defaultContainer = containerColor,
            selectedContent = focusedContentColor,
            defaultContent = contentColor
        ),
        shape = MaterialTheme.shapes.medium
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
    placeholder: String = ""
) {
    val interactionSource = remember { MutableInteractionSource() }
    val IsFocused by interactionSource.collectIsFocusedAsState()

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        interactionSource = interactionSource,
        placeholder = { Text(placeholder) },
        colors = appColorTextFieldAnim(
            isSelected = IsFocused,
            selectedContainer = focusedContainerColor,
            defaultContainer = containerColor,
            selectedContent = focusedContentColor,
            defaultContent = contentColor
        ),
        shape = MaterialTheme.shapes.medium
    )
}