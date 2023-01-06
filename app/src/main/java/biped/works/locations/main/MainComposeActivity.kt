package biped.works.locations.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import biped.works.compose.collectWithLifecycle
import biped.works.locations.home.ui.HomeScreen
import biped.works.user.settings.data.ThemeSettings
import com.biped.locations.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var themeSettings by mutableStateOf(ThemeSettings())

        setContent {
            viewModel.instruction.collectWithLifecycle { instruction ->
                if (instruction is Instruction.UpdateTheme) {
                    themeSettings = instruction.themeSettings
                }
            }

            AppTheme(
                themeSettings.colorScheme,
                themeSettings.useDynamicColors
            ) {
                HomeScreen()
            }
        }
    }
}