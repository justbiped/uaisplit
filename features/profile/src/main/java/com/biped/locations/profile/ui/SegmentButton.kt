package com.biped.locations.profile.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.biped.locations.theme.colorScheme
import com.biped.locations.theme.components.LargeLabel

@Composable
fun SegmentedButton(
    segments: SnapshotStateList<SegmentItem>,
    multiSegments: Boolean = false,
    colors: SegmentColors = segmentColors(),
    dimension: SegmentDimension = SegmentDimension(),
    onSegmentSelected: (Map<String, Boolean>) -> Unit = {}
) {
    val selectedItems = remember { createSelectionMap(segments) }
    var lastSelectedKey = segments.first { it.isSelected }.key
    val shape = CircleShape

    fun changeSingleSelection(key: String) {
        if (selectedItems[key] == false) selectedItems[key] = true
        if (lastSelectedKey != key) {
            selectedItems.replace(lastSelectedKey, false)
            lastSelectedKey = key
        }
    }

    fun changeSegmentSelection(key: String) {
        if (multiSegments.not()) {
            changeSingleSelection(key)
        } else {
            selectedItems[key] = selectedItems[key]!!.not()
        }

        onSegmentSelected(selectedItems)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimension.height)
            .border(dimension.thickness, colors.line, shape),
        shape = shape,
        color = colors.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
        ) {
            segments.forEach { segment ->
                Segment(
                    label = segment.label,
                    key = segment.key,
                    isSelected = segment.isSelected,
                    onClick = { key -> changeSegmentSelection(key) },
                    modifier = Modifier.weight(1f)
                )

                if (segments.size > 1) SegmentDivider()
            }
        }
    }
}

@Composable
private fun SegmentDivider(
    colors: SegmentColors = segmentColors(),
    dimension: SegmentDimension = SegmentDimension()
) {
    Divider(
        color = colors.line,
        modifier = Modifier
            .fillMaxHeight()
            .width(dimension.thickness)
    )
}

@Composable
private fun Segment(
    label: String,
    key: String,
    onClick: (String) -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    segmentColors: SegmentColors = segmentColors(),
) {
    val background by animateColorAsState(
        targetValue = if (isSelected) segmentColors.backgroundSelected else segmentColors.background,
        animationSpec = colorAnimationSpec()
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) segmentColors.textColorSelected else segmentColors.textColor
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .background(background)
            .clickable { onClick(key) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LargeLabel(text = label, color = textColor)
    }
}

@Composable
private fun segmentColors(): SegmentColors {
    return SegmentColors(
        line = colorScheme().outline,
        background = Color.Transparent,
        textColor = colorScheme().onSurface,
        backgroundSelected = colorScheme().primary,
        textColorSelected = colorScheme().onSecondaryContainer
    )
}

data class SegmentColors(
    val line: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
    val backgroundSelected: Color = Color.Unspecified,
    val textColorSelected: Color = Color.Unspecified
)

data class SegmentDimension(
    val height: Dp = 40.dp,
    val thickness: Dp = 1.dp
)

data class SegmentItem(
    val label: String,
    val key: String = label,
    val isSelected: Boolean = false,
    val textColor: Color = Color.Unspecified
)

private fun colorAnimationSpec() = tween<Color>(
    durationMillis = 450,
    delayMillis = 50,
    easing = LinearOutSlowInEasing
)

private fun createSelectionMap(segments: List<SegmentItem>): SnapshotStateMap<String, Boolean> {
    return SnapshotStateMap<String, Boolean>().apply {
        segments.forEach { segment -> put(segment.key, segment.isSelected) }
    }
}