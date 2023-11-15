package biped.works.transaction.ui

import androidx.lifecycle.ViewModel
import biped.works.coroutines.MutableUiStateFlow
import biped.works.transaction.ObserveTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject 


@HiltViewModel
internal class TransactionsViewModel @Inject constructor(
    private val transactionsUseCase: ObserveTransactionsUseCase) : ViewModel()
{

    private val _instruction = MutableUiStateFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toUiStateFlow()

}