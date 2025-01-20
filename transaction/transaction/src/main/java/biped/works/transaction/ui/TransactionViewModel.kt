package biped.works.transaction.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.asUiState
import biped.works.coroutines.launchIO
import biped.works.coroutines.mutableEventFlow
import biped.works.transaction.GetTransactionUseCase
import biped.works.transaction.UpdateTransactionUseCase
import biped.works.transaction.data.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
internal class TransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTransactionUseCase: GetTransactionUseCase,
    private val saveTransactionUseCase: UpdateTransactionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TransactionState>(TransactionState())
    val uiState = _uiState.asUiState(viewModelScope)

    val uiEvent: Flow<TransactionEvent>
        field = mutableEventFlow<TransactionEvent>()

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
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launchIO {
            getTransactionUseCase(id)
                .onSuccess { transaction -> onTransactionUpdate(transaction) }
                .onFailure { onLoadTransactionError() }
        }
    }

    private fun onTransactionUpdate(transaction: Transaction) {
        _uiState.update {
            TransactionState(isLoading = false, transaction.toUiModel())
        }
    }

    private fun onLoadTransactionError() {
        _uiState.update { it.copy(isLoading = false) }
        uiEvent.tryEmit(TransactionEvent.FailedToUpdate)
    }
}