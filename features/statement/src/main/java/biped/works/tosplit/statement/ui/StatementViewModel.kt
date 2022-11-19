package biped.works.tosplit.statement.ui

import androidx.lifecycle.ViewModel
import biped.works.coroutines.MutableWarmFlow
import biped.works.tosplit.statement.LoadStatementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class StatementViewModel @Inject constructor(
    loadStatement: LoadStatementUseCase
) : ViewModel() {

    private val _instruction = MutableWarmFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toWarmFlow()

    init {
        loadStatement()
    }
}
