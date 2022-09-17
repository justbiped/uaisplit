package com.biped.locations.profile.ui

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.biped.locations.theme.colorScheme
import com.biped.locations.theme.components.LargeLabel

@Composable
fun SegmentButton(
    segments: SnapshotStateList<SegmentItem>,
    colors: SegmentColors = segmentColors(),
    dimension: SegmentDimension = SegmentDimension(),
    shape: Shape = CircleShape,
    onSegmentSelected: (Any) -> Unit = {}
) {
    var selectedKey by remember(segments) { mutableStateOf(getSelectedKey(segments)) }

    fun changeSegmentSelection(key: Any) {
        if (key != selectedKey) {
            selectedKey = key
            onSegmentSelected(selectedKey)
        }
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
                SegmentBox(
                    segment,
                    isSelected = selectedKey == segment.key,
                    onClick = { key -> changeSegmentSelection(key) },
                    modifier = Modifier.weight(1f),
                    segmentColors = colors
                )

                if (segments.size > 1) SegmentDivider(colors = colors)
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
private fun SegmentBox(
    segment: SegmentItem,
    onClick: (key: Any) -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    segmentColors: SegmentColors = segmentColors(),
) {
    val background by animateColorAsState(
        targetValue = if (isSelected) segmentColors.backgroundSelected else segmentColors.background
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) segmentColors.textColorSelected else segmentColors.textColor
    )

    Row(
        modifier = modifier
            .fillMaxSize()
            .background(background)
            .clickable { onClick(segment.key) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LargeLabel(text = segment.label, color = textColor)
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
    val key: Any = label,
    val isSelected: Boolean = false
)

private fun getSelectedKey(segments: List<SegmentItem>): Any {
    return segments.firstOrNull { it.isSelected }?.key ?: Any()
}

@Composable
fun rememberSegmentState(vararg segment: SegmentItem): SnapshotStateList<SegmentItem> =
    remember(segment) { SnapshotStateList<SegmentItem>().also { it.addAll(segment) } }