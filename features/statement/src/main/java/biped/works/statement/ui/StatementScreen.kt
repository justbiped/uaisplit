package biped.works.statement.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import biped.works.compose.collectWithLifecycle
import biped.works.statement.data.Statement
import biped.works.statement.data.Transaction
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.components.LargeBody
import com.biped.locations.theme.components.LargeDisplayText
import com.biped.locations.theme.components.MediumTitle

@Composable
internal fun StatementScreen(viewModel: StatementViewModel) {
    var state by remember { mutableStateOf(StatementInstruction.State()) }

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is StatementInstruction.State -> state = instruction
            is StatementInstruction.FailedToFetchStatement -> print("blabla")
        }
    }

    when {
        state.isLoading -> LoadingUi()
        state.isEmpty -> EmptyStatementUi()
        else -> StatementUi(state.uiModel)
    }
}

@Composable
private fun LoadingUi() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptyStatementUi() {
    LargeDisplayText(text = "You don't have any transaction registered yet, try to create one.")
}

@Composable
private fun StatementUi(statement: Statement) {
    LazyColumn {
        items(statement.transactions) { transaction ->
            TransactionCell(transaction)
        }
    }
}

@Composable
private fun TransactionCell(transaction: Transaction) {
    Column(
        Modifier
            .padding(horizontal = Dimens.small)
            .padding(vertical = Dimens.normal)
    ) {
        LargeBody(text = transaction.description)
        MediumTitle(text = transaction.value.toString())
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun TransactionCell_Preview() {
    CashTheme {
        TransactionCell(transaction = Transaction("", "Preview Transaction", 2300.00))
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