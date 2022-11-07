package biped.works.locations.home

import com.biped.locations.settings.ThemeSettings

sealed interface HomeInstruction {
    object Default : HomeInstruction
    data class UpdateTheme(val themeSettings: ThemeSettings) : HomeInstruction
    data class Navigate(val destination: HomeDestination) : HomeInstruction
}