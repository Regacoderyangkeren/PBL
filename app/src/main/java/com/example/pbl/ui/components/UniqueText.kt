package com.example.pbl.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pbl.R
import com.example.pbl.ui.theme.PBLTheme

// for them line beneath the textfield (only shows when focused or has been focused)
@Composable
fun FieldHintRow(
    hint: String,
    errorMsg: String,
    isPassed: Boolean,
    isFocused: Boolean,
    hasBeenFocused: Boolean,
    shouldValidate: Boolean,
    modifier: Modifier = Modifier
) {
    // Show hints if currently focused OR has been focused before
    if (!isFocused && !hasBeenFocused) return

    val customColors = PBLTheme.colors
    val tint: Color
    val label: String
    val showIcon: Boolean

    when {
        !shouldValidate -> {
            // gray and bullet before anything
            tint = Color.Gray
            label = hint
            showIcon = false
        }
        // when passed, turned to green and check
        isPassed -> {
            tint = customColors.success
            label = hint
            showIcon = true
        }
        // when failed, turned to red and cross
        else -> {
            tint = MaterialTheme.colorScheme.error
            label = errorMsg
            showIcon = true
        }
    }

    Row(
        modifier = modifier.padding(start = 4.dp, top = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showIcon) {
            val iconRes = if (isPassed) R.drawable.green_check else R.drawable.red_cross
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(12.dp)
            )
        } else {
            Text(
                text = "\u2022",
                color = tint,
                style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(Modifier.width(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = tint
        )
    }
}
