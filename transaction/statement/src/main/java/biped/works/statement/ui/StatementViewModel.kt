package biped.works.statement.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.asUiState
import biped.works.coroutines.launchIO
import biped.works.coroutines.mutableEventFlow
import biped.works.statement.FetchStatementUseCase
import biped.works.statement.data.Statement
import biped.works.statement.data.TimeSpan
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
internal class StatementViewModel @Inject constructor(
    private val fetchStatementUseCase: FetchStatementUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatementState())
    val uiState = _uiState.asUiState(viewModelScope)

    val uiEvent: Flow<StatementEvent>
        field = mutableEventFlow<StatementEvent>()

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
        _uiState.update {
            it.copy(uiModel = statement, isLoading = false)
        }
    }

    fun addTransaction() {
        uiEvent.tryEmit(StatementEvent.AddTransaction)
    }

    fun openTransaction(transactionId: String) {
        uiEvent.tryEmit(StatementEvent.OpenTransaction(transactionId))
    }

    private fun YearMonth.toLocalDate(day: Int) = LocalDate.of(year, month.value, day)
}