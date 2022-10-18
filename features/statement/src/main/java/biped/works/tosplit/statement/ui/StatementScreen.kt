package biped.works.tosplit.statement.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import biped.works.tosplit.statement.data.Statement
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.components.LargeLabel

private data class ComposeState(
    val data: List<Statement> = emptyList(),
    val isLoading: Boolean = false
) {
    fun default() = copy(isLoading = false)
    fun loading() = copy(isLoading = true)
    fun showStatement(data: List<Statement>) = copy(data = data, isLoading = false)
}

@Composable
internal fun StatementScreen(viewModel: StatementViewModel = viewModel()) {
    var state by remember { mutableStateOf(ComposeState()) }

    LaunchedEffect(Unit) {
        viewModel.instruction.collect { instruction ->
            state = when (instruction) {
                is Instruction.Default -> state.default()
                is Instruction.Loading -> state.loading()
                is Instruction.ShowStatement -> state.showStatement(instruction.statement)
            }
        }
    }

    StatementUi(state = state)
}

@Composable
private fun StatementUi(state: ComposeState) {
    Surface(modifier = Modifier.fillMaxSize()) {
        //todo: implement a lazy list
        LargeLabel(text = "Suas continhas")
    }
}

@Composable
@Preview(showBackground = true)
private fun Statement_Preview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            StatementUi(state = ComposeState())
        }
    }
}