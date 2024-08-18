package biped.works.statement.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import biped.works.compose.collectWithLifecycle
import biped.works.statement.data.Statement
import biped.works.statement.data.TimeSpan
import biped.works.statement.data.Transaction
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.components.LargeDisplayText
import com.biped.locations.theme.components.LargeTitle
import com.biped.locations.theme.components.Loading

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

    when {
        state.isLoading -> Loading()
        state.isEmpty -> EmptyStatement()
        else -> StatementPanel(
            statement = state.uiModel,
            onTransactionClick = viewModel::openTransaction
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatementPanel(statement: Statement, onTransactionClick: (String) -> Unit) {
    CollapsingLayout(
        minHeight = 48.dp,
        header = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        CashTheme.colorScheme.surfaceContainer,
                        RoundedCornerShape(bottomStart = Dimens.normal, bottomEnd = Dimens.normal)
                    ),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(Dimens.normal)
                        .windowInsetsPadding(TopAppBarDefaults.windowInsets),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    LargeTitle(text = "Balance")
                    LargeTitle(text = statement.balance)
                }
            }
        },
        content = { insets ->
            LazyColumn(contentPadding = insets.asPaddingValues()) {
                items(statement.transactions) { transaction ->
                    TransactionCell(
                        transaction,
                        onTransactionClick
                    )
                }
            }
        }
    )
}

@Composable
private fun EmptyStatement() {
    LargeDisplayText(text = "You don't have any transaction registered yet, try to create one.")
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
