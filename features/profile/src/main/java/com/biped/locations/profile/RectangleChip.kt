package com.biped.locations.profile

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.biped.locations.theme.colorScheme
import com.biped.locations.theme.components.LargeLabel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RectangleChip(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    selected: Boolean
) {
    val backgroundColor = if (selected) colorScheme().primary else Color.Transparent
    val textColor = if (selected) colorScheme().onPrimary else colorScheme().onBackground
    val border = BorderStroke(width = 0.8.dp, color = colorScheme().surfaceTint)
    OutlinedButton(
        modifier = modifier
            .background(backgroundColor)
            .height(AssistChipDefaults.Height),
        shape = RectangleShape,
        onClick = onClick,
        border = border
    ) {
        LargeLabel(text = label, color = textColor)
    }
}