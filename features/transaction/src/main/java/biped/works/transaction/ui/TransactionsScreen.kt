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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
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

    TransactionsUi(state = state)
}

@Composable
private fun TransactionsUi(state: TransactionsState) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        for (i in 0..11) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .onVisibilityChange { Log.d("VISIBILITY", "$it -- $i") }
                    .background(Color.Green)
            )
            Spacer(Modifier.padding(top = 6.dp))
        }
    }
}

fun Modifier.onVisibilityChange(action: (Boolean) -> Unit) = composed {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(isVisible) { action(isVisible) }

    this.onGloballyPositioned {
        val bounds = it.boundsInWindow()

        isVisible = if (it.isAttached) {
            val heightVisibility = bounds.height / it.size.height
            val widthVisibility = bounds.width / it.size.width
            heightVisibility >= 0.8 && widthVisibility >= 0.8
        } else {
            false
        }
    }
}