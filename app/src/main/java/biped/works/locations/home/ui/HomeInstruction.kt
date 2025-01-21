package biped.works.locations.home.ui

import androidx.compose.runtime.Stable
import biped.works.locations.home.HomeNavigation

sealed interface HomeEvent {
    data class Navigate(val destination: Any) : HomeEvent
}