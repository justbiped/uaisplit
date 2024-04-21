package com.biped.locations.theme

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    small = RoundedCornerShape(16.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(0.dp)
)

@Preview
@Composable
fun Shapes() {
    CashTheme {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
        ) {

        }
    }
}