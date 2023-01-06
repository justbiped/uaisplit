package biped.works.locations.main

import biped.works.user.settings.data.ThemeSettings

internal sealed interface Instruction {
    object Default : Instruction
    data class UpdateTheme(val themeSettings: ThemeSettings) : Instruction
}