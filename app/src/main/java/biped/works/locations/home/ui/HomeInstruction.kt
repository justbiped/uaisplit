package biped.works.locations.home.ui

sealed interface HomeInstruction {
    object Default : HomeInstruction
    data class Navigate(val destination: Any) : HomeInstruction
}