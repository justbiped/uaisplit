package biped.works.transaction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableUiStateFlow
import biped.works.coroutines.launchIO
import biped.works.transaction.ListTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TransactionsViewModel @Inject constructor(
    listTransactionUseCase: ListTransactionUseCase
) : ViewModel() {

    private val _instruction = MutableUiStateFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toUiStateFlow()

    init {
        viewModelScope.launchIO {
            listTransactionUseCase()
        }
    }
}