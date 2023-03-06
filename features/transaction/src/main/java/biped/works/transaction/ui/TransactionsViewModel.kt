package biped.works.transaction.ui

import androidx.lifecycle.ViewModel
import biped.works.coroutines.MutableInstructionFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TransactionsViewModel @Inject constructor(

) : ViewModel() {

    private val _instruction = MutableInstructionFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toInstructionFlow()

}