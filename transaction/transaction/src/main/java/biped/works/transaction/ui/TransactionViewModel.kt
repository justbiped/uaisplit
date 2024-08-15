package biped.works.transaction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableUiStateFlow
import biped.works.coroutines.UiStateFlow
import biped.works.coroutines.launchIO
import biped.works.transaction.GetTransactionUseCase
import biped.works.transaction.data.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TransactionViewModel @Inject constructor(
    private val getTransactionUseCase: GetTransactionUseCase
) : ViewModel() {

    val instruction: UiStateFlow<TransactionInstruction>
        field = MutableUiStateFlow<TransactionInstruction>(TransactionInstruction.State())

    init {
        viewModelScope.launchIO {
            getTransactionUseCase("20240110zMMSmPHpjTNOwiJhCOQ9")
                .onSuccess { transaction -> onTransactionUpdate(transaction) }
                .onFailure { instruction.sendEvent(TransactionInstruction.FailedToUpdate) }
        }
    }

    private fun onTransactionUpdate(transaction: Transaction) {
        instruction.updateState {
            TransactionInstruction.State(isLoading = false, transaction.toUiModel())
        }
    }
}