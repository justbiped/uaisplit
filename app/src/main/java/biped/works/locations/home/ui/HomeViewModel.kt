package biped.works.locations.home.ui

import androidx.lifecycle.ViewModel
import biped.works.coroutines.MutableUiStateFlow
import biped.works.locations.home.HomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    private val _instruction = MutableUiStateFlow<HomeInstruction>(HomeInstruction.Default)
    val instruction = _instruction.toUiStateFlow()

    fun selectHomeDestination(destination: HomeDestination) {
        _instruction.sendEvent(HomeInstruction.Navigate(destination))
    }
}