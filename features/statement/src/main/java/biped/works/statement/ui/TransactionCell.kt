package biped.works.statement.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import biped.works.statement.data.Transaction
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.components.LargeBody

@Composable
fun TransactionCell(transaction: Transaction, onClick: (String) -> Unit) {
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
                LargeBody(text = "$${transaction.value}")
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