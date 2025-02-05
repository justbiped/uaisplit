package biped.works.locations.home.ui

import androidx.lifecycle.ViewModel
import biped.works.coroutines.mutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    val uiEvent = mutableEventFlow<HomeEvent>()

    fun selectHomeDestination(destination: Any) {
        uiEvent.tryEmit(HomeEvent.Navigate(destination))
    }
}