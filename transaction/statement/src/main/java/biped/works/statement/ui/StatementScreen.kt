package biped.works.statement.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import biped.works.compose.collectWithLifecycle
import biped.works.statement.data.Statement
import biped.works.statement.data.TimeSpan
import biped.works.statement.data.Transaction
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.components.LargeDisplayText
import com.biped.locations.theme.components.Loading
import java.time.YearMonth

@Composable
internal fun StatementScreen(viewModel: StatementViewModel, onNavigate: (destination: Any) -> Unit) {
    var state by remember { mutableStateOf(StatementInstruction.State()) }
    val context = LocalContext.current

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is StatementInstruction.State -> state = instruction
            is StatementInstruction.OpenTransaction -> onNavigate(instruction.destination)
            is StatementInstruction.FailedToFetchStatement -> showFetchFailToast(context)
        }
    }

    StatementPanel(
        statement = state.uiModel,
        isLoading = state.isLoading,
        isEmpty = state.isEmpty,
        onTransactionClick = viewModel::openTransaction,
        onMonthSelected = viewModel::loadStatement
    )
}

@Composable
private fun StatementPanel(
    statement: Statement,
    isLoading: Boolean = false,
    isEmpty: Boolean = false,
    onTransactionClick: (String) -> Unit = {},
    onMonthSelected: (YearMonth) -> Unit = {}
) {
    val minHeight = getTopWindowInset() + 60.dp
    CollapsingLayout(
        minHeight = minHeight,
        header = { BalanceHeader(statement, onMonthSelected = onMonthSelected) }) { insets ->
        when {
            isLoading -> Loading()
            isEmpty -> EmptyStatement()
            else -> {
                LazyColumn(contentPadding = insets.asPaddingValues()) {
                    items(statement.transactions) { transaction ->
                        TransactionCell(
                            transaction,
                            onTransactionClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BalanceHeader(statement: Statement, onMonthSelected: (YearMonth) -> Unit = {}) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                CashTheme.colorScheme.surfaceContainer,
                RoundedCornerShape(bottomStart = Dimens.normal, bottomEnd = Dimens.normal)
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        MonthSelector(onMonthSelected = onMonthSelected)
    }
}

@Composable
private fun EmptyStatement() {
    LargeDisplayText(text = "You don't have any transaction registered yet, try to create one.")
}

@Composable
private fun getTopWindowInset() = with(LocalDensity.current) {
    val topInset = WindowInsets.systemBars.getTop(LocalDensity.current)
    topInset.toDp()
}

fun showFetchFailToast(context: Context) {
    Toast.makeText(context, "Unable to update", Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true, device = "id:pixel_8")
@Composable
fun StatementUi_Preview() {
    CashTheme {
        StatementPanel(
            statement = Statement(
                "Balance",
                transactions = listOf(
                    Transaction(
                        id = "",
                        name = "Test Transaction",
                        description = "Some Description here",
                        value = 300.00
                    ),
                    Transaction(
                        id = "",
                        name = "Test Transaction",
                        description = "Some Description here",
                        value = 300.00
                    )
                ),
                timeSpan = TimeSpan("", "")
            ),
            onTransactionClick = {}
        )
    }
}
