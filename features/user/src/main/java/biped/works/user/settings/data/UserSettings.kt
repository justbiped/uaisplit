package biped.works.user.settings.data

data class UserSettings(
    val userId: String = "",
    val name: String = "",
    val picture: String = "",
    val theme: ThemeSettings = ThemeSettings()
)