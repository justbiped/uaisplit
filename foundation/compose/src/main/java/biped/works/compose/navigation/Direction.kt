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

data object ProfileGraph : Graph("profile_graph") {
    data object Profile : Direction("profile")
}
