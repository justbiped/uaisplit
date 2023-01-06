package biped.works.locations.home.ui

import biped.works.locations.home.HomeDestination

sealed interface HomeInstruction {
    object Default : HomeInstruction
    data class Navigate(val destination: HomeDestination) : HomeInstruction
}