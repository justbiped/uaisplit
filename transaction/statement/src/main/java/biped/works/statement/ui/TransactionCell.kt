package biped.works.statement.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import biped.works.statement.R
import biped.works.statement.data.Transaction
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.TinySpacer
import com.biped.locations.theme.components.LargeBody

@Composable
fun TransactionCell(transaction: Transaction, onClick: (String) -> Unit) {
    val balanceIndicator = if (transaction.value < 0) R.drawable.debt_arrow else R.drawable.credit_arrow

    Row(modifier = Modifier.clickable { onClick(transaction.id) }) {
        Column(
            Modifier
                .padding(horizontal = Dimens.small)
                .padding(vertical = Dimens.normal)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                LargeBody(text = transaction.description)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = balanceIndicator), null, tint = CashTheme.colorScheme.primary)
                    TinySpacer()
                    LargeBody(text = "$${transaction.value}")
                }

            }

        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun TransactionCell_Preview() {
    CashTheme {
        TransactionCell(
            onClick = {},
            transaction = Transaction("", "Preview Transaction", 2300.00)
        )
    }
}