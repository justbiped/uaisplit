package biped.works.statement.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableUiStateFlow
import biped.works.statement.ObserveStatementUseCase
import biped.works.statement.data.Statement
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
internal class StatementViewModel @Inject constructor(
   // observeStatement: ObserveStatementUseCase
) : ViewModel() {

    private val _instruction = MutableUiStateFlow<StatementInstruction>(StatementInstruction.State())
    val instruction = _instruction.toUiStateFlow()

//    init {
//        observeStatement()
//            .onEach { onStatementUpdate(it) }
//            .launchIn(viewModelScope)
//    }
//
//    private fun onStatementUpdate(statement: Statement) {
//
//    }

}