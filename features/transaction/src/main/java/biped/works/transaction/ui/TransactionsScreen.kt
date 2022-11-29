package biped.works.transaction.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
}