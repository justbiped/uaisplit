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
import biped.works.locations.R
import biped.works.transaction.navigation.TransactionNavGraph
import biped.works.transaction.navigation.transactionNavGraph
import com.biped.locations.user.ProfileNavGraph
import com.biped.locations.user.profileNavGraph

sealed class HomeDestination(
    val graph: String,
    val route: String,
    @StringRes val title: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    object Transaction : HomeDestination(
        graph = TransactionNavGraph.route,
        route = TransactionNavGraph.startDestination,
        title = R.string.transaction_list_label,
        unselectedIcon = Icons.Outlined.ViewList,
        selectedIcon = Icons.Filled.ViewList
    )

    object UserSettings : HomeDestination(
        graph = ProfileNavGraph.route,
        route = ProfileNavGraph.startDestination,
        title = R.string.profile_destination_label,
        unselectedIcon = Icons.Outlined.People,
        selectedIcon = Icons.Filled.People
    )

    companion object {
        private val homeDestinationsSet = hashSetOf(
            TransactionNavGraph.startDestination,
            ProfileNavGraph.startDestination
        )

        fun contains(route: String) = homeDestinationsSet.contains(route)
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = HomeDestination.Transaction.graph) {
        transactionNavGraph(navController)
        profileNavGraph(navController)
    }
}
