package biped.works.statement.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBackIos
import androidx.compose.material.icons.automirrored.sharp.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.components.LargeTitle
import java.time.YearMonth
import java.time.format.TextStyle

@Composable
fun MonthSelector(start: YearMonth = YearMonth.now()) {
    var currentYearMonth by remember { mutableStateOf(start) }
    var isIncrement by remember { mutableStateOf(false) }

    fun incrementMonth() {
        isIncrement = true
        currentYearMonth = currentYearMonth.plusMonths(1)
    }

    fun decrementMonth() {
        isIncrement = false
        currentYearMonth = currentYearMonth.plusMonths(-1)
    }

    Row(
        Modifier
            .fillMaxWidth()
            .height(58.dp)
            .padding(vertical = Dimens.small)
            .padding(horizontal = Dimens.big),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(onClick = { incrementMonth() }) {
            Icon(Icons.AutoMirrored.Sharp.ArrowBackIos, null)
        }
        AnimatedContent(
            targetState = currentYearMonth,
            transitionSpec = {
                if (isIncrement) {
                    (slideInHorizontally { width -> -width } + fadeIn())
                        .togetherWith(slideOutHorizontally { width -> width } + fadeOut())
                } else {
                    (slideInHorizontally { width -> width } + fadeIn())
                        .togetherWith(slideOutHorizontally { width -> -width } + fadeOut())
                }.using(
                    SizeTransform(clip = false)
                )
            },
            label = ""
        ) { yearMonth ->
            LargeTitle(
                yearMonth.month.getDisplayName(TextStyle.FULL, Locale.current.platformLocale)
            )
        }
        IconButton(onClick = { decrementMonth() }) {
            Icon(
                Icons.AutoMirrored.Sharp.ArrowForwardIos, null
            )
        }
    }

}

@Preview
@Composable
fun MonthSelector_Preview() {
    CashTheme {
        Box(Modifier.background(CashTheme.colorScheme.surfaceContainer)) {
            MonthSelector()
        }
    }
}