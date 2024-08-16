package biped.works.statement.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import biped.works.statement.R
import biped.works.statement.data.Transaction
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.TinySpacer
import com.biped.locations.theme.components.LargeBody
import com.biped.locations.theme.components.MediumBody
import com.biped.locations.theme.components.MediumTitle

@Composable
fun TransactionCell(
    transaction: Transaction,
    onClick: (String) -> Unit
) {
    Row(modifier = Modifier.clickable { onClick(transaction.id) }) {
        Column(
            Modifier
                .padding(horizontal = Dimens.normal)
                .padding(vertical = Dimens.normal)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MediumTitle(text = transaction.name)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BalanceIcon(transaction.value)
                    TinySpacer()
                    LargeBody(text = "$${transaction.value}")
                }
            }
            SmallSpacer()
            MediumBody(text = transaction.description)
        }
    }
}

@Composable
private fun BalanceIcon(value: Double) {
    val (balanceIndicator, balanceDescription) = if (value < 0) {
        Pair(R.drawable.debt_arrow, "Negative balance icon")
    } else {
        Pair(R.drawable.credit_arrow, "Positive balance icon")
    }

    Icon(
        painter = painterResource(id = balanceIndicator),
        tint = CashTheme.colorScheme.primary,
        contentDescription = balanceDescription
    )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun TransactionCell_Preview() {
    CashTheme {
        TransactionCell(
            onClick = {},
            transaction = Transaction(
                "", name = "Rent",
                description = "Preview Transaction",
                2300.00
            )
        )
    }
}