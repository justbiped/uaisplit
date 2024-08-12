package biped.works.transaction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableUiStateFlow
import biped.works.coroutines.launchIO
import biped.works.transaction.GetTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TransactionViewModel @Inject constructor(
    private val getTransactionUseCase: GetTransactionUseCase
) : ViewModel() {

    private val _instruction = MutableUiStateFlow<TransactionInstruction>(TransactionInstruction.State)
    val instruction = _instruction.toUiStateFlow()

    init {
        viewModelScope.launchIO {
            val result = getTransactionUseCase("20240110zMMSmPHpjTNOwiJhCOQ9")
            result.onSuccess {
                print(it)
            }

            result.onFailure {
                print(it)
            }
        }
    }
}