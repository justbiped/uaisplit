package biped.works.locations.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import biped.works.compose.navigation.SettingsGraph
import biped.works.compose.navigation.TransactionGraph
import biped.works.locations.R
import biped.works.transaction.navigation.transactionNavGraph
import com.biped.works.settings.settingsNavGraph

sealed class HomeDestination(
    val graph: String,
    val route: String,
    @StringRes val title: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    object Transaction : HomeDestination(
        graph = TransactionGraph.route,
        route = TransactionGraph.Transaction.route,
        title = R.string.transaction_list_label,
        unselectedIcon = Icons.Outlined.ViewList,
        selectedIcon = Icons.Filled.ViewList
    )

    object UserSettings : HomeDestination(
        graph = SettingsGraph.route,
        route = SettingsGraph.Settings.route,
        title = R.string.profile_destination_label,
        unselectedIcon = Icons.Outlined.People,
        selectedIcon = Icons.Filled.People
    )

    companion object {
        private val homeDestinationsSet = hashSetOf(
            Transaction.route,
            UserSettings.route
        )

        fun contains(route: String) = homeDestinationsSet.contains(route)
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = HomeDestination.Transaction.graph) {
        transactionNavGraph(navController)
        settingsNavGraph(navController)
    }
}
