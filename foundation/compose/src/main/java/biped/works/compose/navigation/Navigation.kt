package biped.works.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.navigation.NavController

val NavController.currentRouteState: State<String>
    @Composable get() = produceState("", this) {
        currentBackStackEntryFlow.collect {
            value = it.destination.route.orEmpty()
        }
    }

