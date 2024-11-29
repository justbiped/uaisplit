package biped.works.transaction.ui

import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.components.ClickableTextField

@Composable
fun DatePickerTextField(
    onDateSelect: (Long) -> Unit,
    initialTime: Long,
    label: @Composable (() -> Unit)? = null,
) {
    var openDatePicker by remember { mutableStateOf(false) }

    ClickableTextField(
        value = formatDate(initialTime),
        onClick = { openDatePicker = true },
        onValueChange = { },
        label = label
    )

    if (openDatePicker) DatePickerModal(
        initialTime = initialTime,
        onDateSelected = onDateSelect,
        onDismiss = { openDatePicker = false })
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun formatDate(initialValue: Long) =
    DatePickerDefaults.dateFormatter().formatDate(initialValue, CalendarLocale.getDefault()) ?: ""

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    initialTime: Long,
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialTime)
    val colors = DatePickerDefaults.colors().copy(containerColor = CashTheme.colorScheme.surfaceContainer)
    DatePickerDialog(
        colors = colors,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.also { onDateSelected(it) }
                onDismiss()
            }) {
                Text("Done")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState, colors = colors)
    }
}