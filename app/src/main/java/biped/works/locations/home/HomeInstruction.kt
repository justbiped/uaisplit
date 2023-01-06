package biped.works.locations.home

import biped.works.user.settings.data.ThemeSettings

sealed interface HomeInstruction {
    object Default : HomeInstruction
    data class UpdateTheme(val themeSettings: ThemeSettings) : HomeInstruction
    data class Navigate(val destination: HomeDestination) : HomeInstruction
}