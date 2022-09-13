package com.biped.locations.profile.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.biped.locations.profile.data.ui.ThemeSettingsUiModel
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.ColorScheme
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.MediumTitle

@Composable
fun ThemeSettingsUi(
    uiModel: ThemeSettingsUiModel,
    onColorScheme: (ColorScheme) -> Unit = {},
    onUseDynamicScheme: (Boolean) -> Unit = {}
) {
    var useDynamicColor by remember { mutableStateOf(uiModel.useDynamicColors) }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MediumTitle(text = "Use dynamic colors")
            Switch(
                checked = useDynamicColor,
                onCheckedChange = { useDynamicColor = it }
            )
        }
        SmallSpacer()
        ColorSchemeSelector(
            colorScheme = uiModel.colorScheme,
            onSchemeSelected = onColorScheme
        )
    }
    onUseDynamicScheme(useDynamicColor)
}

@Composable
fun ColorSchemeSelector(
    colorScheme: ColorScheme,
    onSchemeSelected: (ColorScheme) -> Unit
) {
    val theme by remember { mutableStateOf(colorScheme) }
    val segments = listOf(
        SegmentItem("Dark", isSelected = theme == ColorScheme.DARK),
        SegmentItem("Light", isSelected = theme == ColorScheme.LIGHT),
        SegmentItem("System", isSelected = theme == ColorScheme.SYSTEM),
    )

    SegmentedButton(
        segments = segments,
        onSegmentSelected = { selections ->
            when {
                selections.getOrDefault("Dark", false) -> onSchemeSelected(ColorScheme.DARK)
                selections.getOrDefault("Light", false) -> onSchemeSelected(ColorScheme.LIGHT)
                selections.getOrDefault("System", false) -> onSchemeSelected(ColorScheme.SYSTEM)
            }
        }
    )
}

@Preview(name = "Light preview", showBackground = true)
@Composable
private fun ThemeLightConfigPreview() {
    AppTheme {
        Surface {
            ThemeSettingsUi(ThemeSettingsUiModel())
        }
    }
}

@Preview(name = "Dark preview", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ThemeDarkConfigPreview() {
    AppTheme {
        Surface {
            ThemeSettingsUi(ThemeSettingsUiModel())
        }
    }
}
