package biped.works.transaction.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableUiStateFlow
import biped.works.coroutines.launchIO
import biped.works.transaction.GetTransactionUseCase
import biped.works.transaction.UpdateTransactionUseCase
import biped.works.transaction.data.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTransactionUseCase: GetTransactionUseCase,
    private val saveTransactionUseCase: UpdateTransactionUseCase
) : ViewModel() {

    private val _instruction = MutableUiStateFlow<TransactionInstruction>(TransactionInstruction.State())
    val instruction = _instruction.toUiStateFlow()

    init {
        savedStateHandle.get<String>("id")?.also { id ->
            if (id.isNotBlank()) loadTransaction(id)
        }
    }

    fun saveTransaction(uiModel: TransactionUiModel) {
        viewModelScope.launchIO {
            saveTransactionUseCase(uiModel.toTransactionUpdate())
        }
    }

    private fun loadTransaction(id: String) {
        _instruction.updateState { copy(isLoading = true) }
        viewModelScope.launchIO {
            getTransactionUseCase(id)
                .onSuccess { transaction -> onTransactionUpdate(transaction) }
                .onFailure { onLoadTransactionError() }
        }
    }

    private fun onTransactionUpdate(transaction: Transaction) {
        _instruction.updateState {
            TransactionInstruction.State(isLoading = false, transaction.toUiModel())
        }
    }

    private fun onLoadTransactionError() {
        _instruction.updateState { copy(isLoading = false) }
        _instruction.sendEvent(TransactionInstruction.FailedToUpdate)
    }

    fun onTransactionTypeSelected(uiModel: TransactionUiModel, selectedIndex: Int) {
        val updatedUiModel = uiModel.copy(amount = uiModel.amount + 10) //temp
        _instruction.updateState { copy(uiModel = updatedUiModel) }
    }
}