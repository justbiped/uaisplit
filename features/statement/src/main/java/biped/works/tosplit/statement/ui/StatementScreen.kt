package biped.works.tosplit.statement.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import biped.works.tosplit.statement.data.Transaction
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.components.SmallTitle
import com.favoriteplaces.core.compose.collectWithLifecycle

@Stable
private class StatementState {
    var isLoading: Boolean = false
        private set
    var transactions by mutableStateOf(emptyList<Transaction>())
        private set

    fun default() {
        isLoading = false
    }

    fun loading() {
        isLoading = true
    }

    fun updateStatement(transactions: List<Transaction>) {
        this.transactions = transactions
        isLoading = false
    }
}

@Composable
private fun rememberStatementState() = remember {
    mutableStateOf(StatementState())
}

@Composable
internal fun StatementScreen(viewModel: StatementViewModel = viewModel()) {
    val state by rememberStatementState()

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is Instruction.Default -> state.default()
            is Instruction.Loading -> state.loading()
            is Instruction.UpdateStatement -> state.updateStatement(instruction.transactions)
        }
    }

    StatementUi(state = state)
}

@Composable
private fun StatementUi(state: StatementState) {
    LazyColumn {
        items(state.transactions) {
            TransactionItem(it)
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.small)
    ) {
        Row(
            modifier = Modifier.padding(Dimens.normal),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SmallTitle(text = transaction.description)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            SmallTitle(text = transaction.value.toString())
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Statement_Preview() {
    AppTheme {
        StatementUi(state = StatementState())
    }
}