package biped.works.statement.ui

import androidx.lifecycle.ViewModel
import biped.works.coroutines.MutableUiStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class StatementViewModel @Inject constructor(

) : ViewModel() {

    private val _instruction = MutableUiStateFlow<StatementInstruction>(StatementInstruction.State())
    val instruction = _instruction.toUiStateFlow()

}