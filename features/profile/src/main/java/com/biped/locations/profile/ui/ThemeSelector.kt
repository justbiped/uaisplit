package com.biped.locations.profile.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.biped.locations.profile.data.ui.ThemeSettingsUiModel
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.ColorScheme
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.MediumTitle

@Composable
fun ThemeSettingsUi(
    uiModel: ThemeSettingsUiModel,
    onSettingsChanged: (ThemeSettingsUiModel) -> Unit = {}
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MediumTitle(text = "Use dynamic colors")
            Switch(
                checked = uiModel.useDynamicColors,
                onCheckedChange = { onSettingsChanged(uiModel.copy(useDynamicColors = it)) }
            )
        }
        SmallSpacer()
        ColorSchemeSelector(
            colorScheme = uiModel.colorScheme,
            onSchemeSelected = { onSettingsChanged(uiModel.copy(colorScheme = it)) }
        )
    }
}

@Composable
fun ColorSchemeSelector(
    colorScheme: ColorScheme,
    onSchemeSelected: (ColorScheme) -> Unit
) {
    val segments = rememberSegmentState(
        SegmentItem(
            "Dark",
            key = ColorScheme.DARK,
            isSelected = colorScheme == ColorScheme.DARK
        ),
        SegmentItem(
            "Light",
            key = ColorScheme.LIGHT,
            isSelected = colorScheme == ColorScheme.LIGHT
        ),
        SegmentItem(
            "System",
            key = ColorScheme.SYSTEM,
            isSelected = colorScheme == ColorScheme.SYSTEM
        ),
    )

    SegmentButton(
        modifier = Modifier.fillMaxWidth(),
        segments = segments,
        onSelectionChange = { selections ->
            if (selections.isNotEmpty()) onSchemeSelected(selections.first() as ColorScheme)
        }
    )
}

@Preview(name = "Light preview", showBackground = true)
@Composable
private fun ThemeLightConfigPreview() {
    AppTheme {
        Surface {
            ThemeSettingsUi(settingsUi)
        }
    }
}

@Preview(name = "Dark preview", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ThemeDarkConfigPreview() {
    AppTheme {
        Surface {
            ThemeSettingsUi(settingsUi)
        }
    }
}

private val settingsUi = ThemeSettingsUiModel(ColorScheme.DARK, false)