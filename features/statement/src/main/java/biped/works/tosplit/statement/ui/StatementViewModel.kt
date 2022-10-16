package biped.works.tosplit.statement.ui

import androidx.lifecycle.ViewModel
import biped.works.tosplit.statement.LoadStatementUseCase
import com.favoriteplaces.core.coroutines.MutableWarmFlow
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
