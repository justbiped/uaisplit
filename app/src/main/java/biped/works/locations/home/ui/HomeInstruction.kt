package biped.works.locations.home.ui

import androidx.compose.runtime.Stable

sealed interface HomeEvent {
    data class Navigate(val destination: Any) : HomeEvent
}

@Stable
object HomeUiState