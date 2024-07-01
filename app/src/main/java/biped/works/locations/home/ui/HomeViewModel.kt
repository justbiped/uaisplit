package biped.works.locations.home.ui

import androidx.lifecycle.ViewModel
import biped.works.coroutines.MutableUiStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    private val _instruction = MutableUiStateFlow<HomeInstruction>(HomeInstruction.Default)
    val instruction = _instruction.toUiStateFlow()

    fun selectHomeDestination(destination: Any){
        _instruction.sendEvent(HomeInstruction.Navigate(destination))
    }
}