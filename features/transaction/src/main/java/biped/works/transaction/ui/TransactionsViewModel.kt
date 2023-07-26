package biped.works.transaction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableUiStateFlow
import biped.works.coroutines.launchIO
import biped.works.transaction.data.remote.getTransactionApiInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TransactionsViewModel @Inject constructor() : ViewModel() {

    private val _instruction = MutableUiStateFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toUiStateFlow()

    init {
        carregaTransc()
    }

    fun carregaTransc() {
        viewModelScope.launchIO {
            val result = getTransactionApiInstance().getTransactions("2023-01-01", "2023-01-30")
            print(result)
        }
    }
}