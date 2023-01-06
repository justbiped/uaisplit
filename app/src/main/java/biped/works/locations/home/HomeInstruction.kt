package biped.works.locations.home

sealed interface HomeInstruction {
    object Default : HomeInstruction
    data class Navigate(val destination: HomeDestination) : HomeInstruction
}