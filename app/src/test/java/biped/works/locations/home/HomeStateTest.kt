package biped.works.locations.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import biped.works.compose.navigation.NavGraph
import biped.works.compose.navigation.ProfileGraph
import biped.works.locations.home.ui.HomeState
import biped.works.statement.StatementGraph
import biped.works.test.compose.testNavController
import com.biped.works.settings.SettingsDestination
import com.biped.works.settings.SettingsGraph
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)
//class HomeStateTest {

//    @get:Rule val composeTestRule = createComposeRule()
//
//    @Test
//    fun `current route state updates on destination change`() {
//        var currentRoute = ""
//        composeTestRule.setContent {
//            val navController = testNavController("first_destination", "second_destination")
//            val homeState = HomeState(navController)
//
//            currentRoute = homeState.currentDestination
//            navController.setCurrentDestination("second_destination")
//        }
//
//        assertThat(currentRoute).isEqualTo("second_destination")
//    }
//
//    @Test
//    fun `show bottom nav bar when selected destination is a home menu destination`() {
//        var showBottomBar = false
//
//        composeTestRule.setContent {
//            val navController = homeNavController(homeDestinations)
//            val state = HomeState(navController)
//
//            showBottomBar = state.showBottomBar
//            state.navigate(StatementGraph.startDestination)
//        }
//
//        assertThat(showBottomBar).isTrue()
//    }
//
//    @Test
//    fun `hide bottom nav bar when selected destination is not a home menu destination`() {
//        var showBottomBar = true
//
//        composeTestRule.setContent {
//            val navController = testNavController(
//               ProfileGraph.Profile.route,
//                "no_home_destination"
//            )
//            val state = HomeState(navController)
//
//            showBottomBar = state.showBottomBar
//            navController.setCurrentDestination("no_home_destination")
//        }
//
//        assertThat(showBottomBar).isFalse()
//    }
//
//    private val homeDestinations = listOf(
//        StatementGraph.startDestination,
//        SettingsGraph.startDestination,
//    )
//
//    @Composable
//    fun homeNavController(graphs: List<Any>): TestNavHostController {
//        return testNavController(destinations = graphs.)
//    }
//}