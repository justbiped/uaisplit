package biped.works.locations.main

import com.biped.works.settings.data.ThemeSettings

internal sealed interface Instruction {
    object Default : Instruction
    data class UpdateTheme(val themeSettings: ThemeSettings) : Instruction
}