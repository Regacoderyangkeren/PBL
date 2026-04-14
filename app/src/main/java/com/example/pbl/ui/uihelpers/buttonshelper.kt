package com.example.pbl.ui.uihelpers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// debouncer
@Composable
fun preventButtonSpamClicks(
    onClick: () -> Unit,
    // nge define delay-nya
    delayMillis: Long = 1000L
): () -> Unit {
    val lastClickTime = remember { mutableLongStateOf(0L) }
    val currentOnClick by rememberUpdatedState(onClick)

    return remember(delayMillis) {
        {
            val currentTime = System.currentTimeMillis()
            // nge check apakah ada delay atau tidak
            if (currentTime - lastClickTime.longValue > delayMillis) {
                lastClickTime.longValue = currentTime
                currentOnClick()
            }
        }
    }
}

// Wrapper
@Composable
fun ButtonContentWrapper(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.padding(16.dp)) {
        content()
    }
}