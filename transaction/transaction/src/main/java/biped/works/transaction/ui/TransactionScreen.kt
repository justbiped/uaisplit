package biped.works.transaction.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import biped.works.compose.collectWithLifecycle
import biped.works.transaction.R
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.TinySpacer
import com.biped.locations.theme.components.DropDownItemSelector
import com.biped.locations.theme.components.Loading
import com.biped.locations.theme.components.MenuItem
import com.biped.locations.theme.components.SmallTitle
import com.biped.locations.theme.components.TextField

@Composable
internal fun TransactionScreen(
    viewModel: TransactionViewModel,
    onNavigateUp: () -> Unit
) {
    var state by remember { mutableStateOf(TransactionInstruction.State()) }

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is TransactionInstruction.State -> state = instruction
            is TransactionInstruction.FailedToUpdate -> print("show failure message")
        }
    }

    if (state.isLoading) {
        Loading()
    } else {
        TransactionPanel(
            uiModel = state.uiModel,
            onNavigateUp = onNavigateUp,
            onSave = viewModel::saveTransaction
        )
    }
}

@Composable
fun TransactionPanel(
    uiModel: TransactionUiModel,
    onSave: (TransactionUiModel) -> Unit = {},
    onNavigateUp: () -> Unit = {}
) {
    var form by remember { mutableStateOf(uiModel) }

    Column(
        Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        TopAppbar(onNavigateUp = onNavigateUp, onSave = { onSave(form) })
        SmallSpacer()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = Dimens.small)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = form.name,
                label = { Text(text = stringResource(R.string.transaction_name)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onValueChange = { form = form.copy(name = it) })
            SmallSpacer()
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = form.description,
                label = { Text(stringResource(R.string.transaction_description)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onValueChange = { form = form.copy(description = it) })
            SmallSpacer()
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = form.amount.toString(),
                prefix = { Text(uiModel.currency) },
                label = { Text(stringResource(R.string.transaction_amount)) },
                onValueChange = { })
            SmallSpacer()
            Row {
                DropDownItemSelector(
                    modifier = Modifier.width(100.dp),
                    label = { Text("Currency") },
                    items = listOf(
                        MenuItem("usd", displayText = "$", menuDisplayText = "USD $"),
                        MenuItem("brl", displayText = "R$", menuDisplayText = "BRL R$")
                    ),
                    onSelect = {}
                )
                TinySpacer()
                TextField(value = "12.00", onValueChange = {}, label = { Text("Amount") })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppbar(
    onNavigateUp: () -> Unit, onSave: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Transaction") },
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = onSave) {
                SmallTitle(text = "Save", color = MaterialTheme.colorScheme.tertiary)
            }
        }
    )
}

@Preview
@Composable
fun TransactionPane_Preview() {
    CashTheme {
        TransactionPanel(
            TransactionUiModel(
                id = "24325dfe",
                name = "Car Rent",
                description = "For the trip from x to y",
                due = "13 Feb 2024",
                amount = 320.00,
                currency = "R$"
            )
        )
    }

}