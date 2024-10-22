package biped.works.locations.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import biped.works.compose.collectWithLifecycle
import biped.works.locations.home.ui.HomeScreen
import com.biped.locations.theme.CashTheme
import com.biped.works.settings.data.ThemeSettings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var themeSettings by mutableStateOf(ThemeSettings())
        setContent {
            window.isNavigationBarContrastEnforced = false
            navController = rememberNavController()

            viewModel.instruction.collectWithLifecycle { instruction ->
                if (instruction is Instruction.UpdateTheme) {
                    themeSettings = instruction.themeSettings
                }
            }

            CashTheme(
                themeSettings.colorScheme,
                themeSettings.useDynamicColors
            ) {
                HomeScreen()
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navController.handleDeepLink(intent)
    }
}