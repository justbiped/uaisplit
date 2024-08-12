package biped.works.statement.ui

import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import biped.works.compose.collectWithLifecycle
import biped.works.statement.data.Statement
import biped.works.statement.data.TimeSpan
import com.biped.locations.theme.components.LargeDisplayText
import com.biped.locations.theme.components.LoadingPanel

@Composable
internal fun StatementScreen(viewModel: StatementViewModel, onNavigate: (destination: Any) -> Unit) {
    var state by remember { mutableStateOf(StatementInstruction.State()) }

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is StatementInstruction.State -> state = instruction
            is StatementInstruction.OpenTransaction -> onNavigate(instruction.destination)
            is StatementInstruction.FailedToFetchStatement -> print("blabla")
        }
    }

    when {
        state.isLoading -> LoadingPanel()
        state.isEmpty -> EmptyStatementUi()
        else -> StatementUi(
            statement = state.uiModel,
            onTransactionClick = viewModel::openTransaction
        )
    }
}

@Composable
private fun EmptyStatementUi() {
    LargeDisplayText(text = "You don't have any transaction registered yet, try to create one.")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatementUi(statement: Statement, onTransactionClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .windowInsetsPadding(TopAppBarDefaults.windowInsets)
    ) {
        items(statement.transactions) { transaction ->
            TransactionCell(
                transaction,
                onTransactionClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatementUi_Preview() {
    StatementUi(
        statement = Statement("Balance", emptyList(), timeSpan = TimeSpan("", "")),
        onTransactionClick = {}
    )
}