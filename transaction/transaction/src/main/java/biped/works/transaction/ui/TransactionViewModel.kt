package biped.works.transaction.ui

import androidx.lifecycle.SavedStateHandle
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
    private val savedStateHandle: SavedStateHandle,
    private val getTransactionUseCase: GetTransactionUseCase
) : ViewModel() {

    val instruction: UiStateFlow<TransactionInstruction>
        field = MutableUiStateFlow<TransactionInstruction>(TransactionInstruction.State())

    init {
        savedStateHandle.get<String>("id")?.also { id ->
            if (id.isNotBlank()) loadTransaction(id) else onLoadTransactionError()
        }
    }

    private fun loadTransaction(id: String) {
        viewModelScope.launchIO {
            getTransactionUseCase(id)
                .onSuccess { transaction -> onTransactionUpdate(transaction) }
                .onFailure { onLoadTransactionError() }
        }
    }

    private fun onTransactionUpdate(transaction: Transaction) {
        instruction.updateState {
            TransactionInstruction.State(isLoading = false, transaction.toUiModel())
        }
    }

    fun onLoadTransactionError() {
        instruction.sendEvent(TransactionInstruction.FailedToUpdate)
    }
}