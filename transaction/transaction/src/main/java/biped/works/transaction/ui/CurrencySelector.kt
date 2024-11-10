package biped.works.transaction.ui

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biped.locations.theme.components.DropDownItemSelector
import com.biped.locations.theme.components.MenuItem

@Composable
fun CurrencySelector(
    selected: String = "",
    onSelect: (String) -> Unit
) {
    val supportedCurrencies = listOf(
        MenuItem(Currency.USD.code, displayText = Currency.USD.symbol, menuDisplayText = Currency.USD.toString()),
        MenuItem(Currency.BRL.code, displayText = Currency.BRL.symbol, menuDisplayText = Currency.BRL.toString()),
    )

    val selectedCurrency by remember {
        derivedStateOf {
            supportedCurrencies.firstOrNull { it.key == selected } ?: supportedCurrencies.first()
        }
    }

    DropDownItemSelector(
        modifier = Modifier.width(100.dp),
        label = { Text("Currency") },
        items = supportedCurrencies,
        selected = selectedCurrency,
        onSelect = { onSelect(it.key) }
    )
}

enum class Currency(
    val code: String,
    val symbol: String
) {
    BRL("BRL", "R$"),
    USD("USD", "$");

    override fun toString() = "$code $symbol"
}