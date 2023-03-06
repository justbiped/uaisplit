package biped.works.compose.navigation

import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink

sealed class Graph(val route: String)

sealed class Direction(val route: String) {
    val deepLink: NavDeepLink get() = navDeepLink { uriPattern = "$baseUri/$route" }

    companion object {
        const val baseUri = "http://biped.works"
    }

}

object TransactionGraph : Graph("transaction_graph") {
    object Transaction : Direction("transaction")
}

object SettingsGraph : Graph("settings_graph") {
    object Settings : Direction("settings")
}

object ProfileGraph : Graph("profile_graph") {
    object Profile : Direction("profile")
}
