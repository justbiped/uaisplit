package biped.works.statement.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt

@Composable
fun CollapsingLayout(
    minHeight: Dp = 0.dp,
    header: @Composable ColumnScope.(collapsing: Float) -> Unit,
    content: @Composable (insets: WindowInsets) -> Unit
) {
    val layoutDensity = LocalDensity.current
    var layoutHeightPx by remember { mutableIntStateOf(0) }
    val insets by remember { derivedStateOf { with(layoutDensity) { layoutHeightPx.toDp() } } }
    val minHeightPx by remember {
        val minimum = with(layoutDensity) { minHeight.roundToPx() }
        derivedStateOf { if (minimum == 0) -layoutHeightPx else -minimum }
    }

    var offset by rememberSaveable { mutableIntStateOf(0) }
    val collapseProgress by remember { derivedStateOf { offset.toFloat() / minHeightPx.toFloat() } }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = offset + delta
                offset = newOffset.coerceIn(minHeightPx.toFloat(), 0f).roundToInt()
                return Offset.Zero
            }
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        Column(
            modifier = Modifier
                .zIndex(1f)
                .onSizeChanged { size -> if (layoutHeightPx == 0) layoutHeightPx = size.height }
                .offset { IntOffset(x = 0, y = offset) }
        ) {
            header(collapseProgress)
        }
        content(WindowInsets(top = insets))
    }
}