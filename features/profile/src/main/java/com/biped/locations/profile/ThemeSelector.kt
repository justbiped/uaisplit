package com.biped.locations.profile

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.ColorScheme
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.TinySpacer
import com.biped.locations.theme.components.LargeLabel
import com.biped.locations.theme.components.MediumTitle

data class ThemeSettingsUiModel(
    val colorScheme: ColorScheme = ColorScheme.SYSTEM,
    val useDynamicColors: Boolean = false
)

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
    var theme by remember { mutableStateOf(colorScheme) }

    Row(
        Modifier.fillMaxWidth()
    ) {
        RectangleChip(
            onClick = { theme = ColorScheme.DARK },
            label = "Dark",
            selected = theme == ColorScheme.DARK,
            modifier = weightModifier(theme == ColorScheme.DARK)
        )
        RectangleChip(
            onClick = { theme = ColorScheme.LIGHT },
            label = "Light",
            selected = theme == ColorScheme.LIGHT,
            modifier = weightModifier(theme == ColorScheme.LIGHT)
        )
        RectangleChip(
            onClick = { theme = ColorScheme.SYSTEM },
            label = "System",
            selected = theme == ColorScheme.SYSTEM,
            modifier = weightModifier(theme == ColorScheme.SYSTEM)
        )
    }

    onSchemeSelected(theme)
}

@SuppressLint("ModifierFactoryExtensionFunction")
private fun RowScope.weightModifier(isSelected: Boolean): Modifier {
    val weight = if (isSelected) 1.1f else 1f
    return Modifier
        .weight(weight)
        .animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessHigh))
}

@Preview(name = "Light preview", showBackground = true)
@Composable
private fun ThemeLightConfigPreview() {
    AppTheme {
        ThemeSettingsUi(ThemeSettingsUiModel())
    }
}

@Preview(name = "Dark preview", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ThemeDarkConfigPreview() {
    AppTheme {
        ThemeSettingsUi(ThemeSettingsUiModel())
    }
}
