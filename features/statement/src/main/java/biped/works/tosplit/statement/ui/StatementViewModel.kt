package biped.works.tosplit.statement.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableWarmFlow
import biped.works.tosplit.statement.ObserveTransactionsUseCase
import biped.works.tosplit.statement.data.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
internal class StatementViewModel @Inject constructor(
    observeTransactions: ObserveTransactionsUseCase
) : ViewModel() {

    private val _instruction = MutableWarmFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toWarmFlow()

    init {
        observeTransactions()
            .onEach { onNewTransaction(it) }
            .launchIn(viewModelScope)
    }

    private fun onNewTransaction(transactions: List<Transaction>) {
        _instruction.post(Instruction.UpdateStatement(transactions))
    }
}
