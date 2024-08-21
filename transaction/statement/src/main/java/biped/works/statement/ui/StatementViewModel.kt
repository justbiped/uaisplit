package biped.works.statement.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableUiStateFlow
import biped.works.coroutines.launchIO
import biped.works.statement.FetchStatementUseCase
import biped.works.statement.data.Statement
import biped.works.statement.data.TimeSpan
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
internal class StatementViewModel @Inject constructor(
    private val fetchStatementUseCase: FetchStatementUseCase
) : ViewModel() {

    private val _instruction = MutableUiStateFlow<StatementInstruction>(StatementInstruction.State())
    val instruction = _instruction.toUiStateFlow()

    fun loadStatement(yearMonth: YearMonth) {
        val timeSpan = TimeSpan(
            yearMonth.toLocalDate(1).toString(),
            yearMonth.toLocalDate(yearMonth.lengthOfMonth()).toString()
        )
        viewModelScope.launchIO {
            fetchStatementUseCase(timeSpan)
                .onSuccess { onStatementUpdate(it) }
        }
    }

    private fun onStatementUpdate(statement: Statement) {
        _instruction.updateState {
            copy(uiModel = statement, isLoading = false)
        }
    }

    fun addTransaction() {
        // todo: Navigate to transaction screen
    }

    fun openTransaction(transactionId: String) {
        _instruction.sendEvent(StatementInstruction.OpenTransaction(transactionId))
    }

    private fun YearMonth.toLocalDate(day: Int) = LocalDate.of(year, month.value, day)
}