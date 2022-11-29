package biped.works.transaction.ui

import androidx.lifecycle.ViewModel
import biped.works.coroutines.MutableWarmFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TransactionsViewModel @Inject constructor(

) : ViewModel() {

    private val _instruction = MutableWarmFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toWarmFlow()

}