package biped.works.transaction.ui

import com.favoriteplaces.core.date.asEpoch
import com.favoriteplaces.core.date.currentEpoch
import com.favoriteplaces.core.date.formatAsDate

data class TransactionUiModel(
    val id: String = "",
    val owner: String = "",
    val name: String = "",
    val description: String = "",
    val due: String = currentEpoch().formatAsDate(),
    val amount: String = "",
    val currency: String = ""
) {
    val dueEpoch = due.asEpoch()
}