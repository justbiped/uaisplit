package biped.works.locations.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.asUiState
import biped.works.coroutines.mutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState)
    val uiState = _uiState.asUiState(viewModelScope)

    val uiEvent = mutableEventFlow<HomeEvent>()

    fun selectHomeDestination(destination: Any) {
        uiEvent.tryEmit(HomeEvent.Navigate(destination))
    }
}