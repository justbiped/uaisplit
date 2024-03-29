package biped.works.transaction.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import biped.works.compose.collectWithLifecycle

@Stable
private class TransactionsState {
    var isLoading by mutableStateOf(false)
        private set

    fun default() {
        isLoading = false
    }

    fun loading() {
        isLoading = true
    }
}

@Composable
private fun rememberTransactionsState() = remember {
    mutableStateOf(TransactionsState())
}

@Composable
internal fun TransactionsScreen(viewModel: TransactionsViewModel = viewModel()) {
    val state by rememberTransactionsState()

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is Instruction.Default -> state.default()
            is Instruction.Loading -> state.loading()
        }
    }

    ColumnTest()
}

@Composable
private fun ColumnTest() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        for (i in 0..20) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .onVisibilityChange { Log.d("VISIBILITY", "visible: $it | index:$i") }
                    .background(Color.Green)
            )
            Spacer(Modifier.padding(top = 6.dp))
        }
    }
}

fun Modifier.onVisibilityChange(action: (Visibility) -> Unit) = composed {
    val visibilityChanged by rememberUpdatedState(action)
    var inBoundsVisibility by remember { mutableStateOf(Visibility.GONE) }

    LaunchedEffect(inBoundsVisibility) {
        visibilityChanged(inBoundsVisibility)
    }

    onGloballyPositioned { coordinates -> inBoundsVisibility = coordinates.measureInBoundsVisibility() }
}

private fun LayoutCoordinates.measureInBoundsVisibility(): Visibility {
    val bounds = boundsInWindow()
    val size = size

    val visibilityRatio = (bounds.height / size.height) * (bounds.width / size.width)
    return if (isAttached) {
        when {
            visibilityRatio <= 0f -> Visibility.GONE
            visibilityRatio >= 0.8 -> Visibility.VISIBLE
            else -> Visibility.PARTIAL
        }
    } else {
        Visibility.GONE
    }
}

enum class Visibility {
    VISIBLE,
    PARTIAL,
    GONE,
}