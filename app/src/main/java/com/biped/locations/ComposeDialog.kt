package com.biped.locations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biped.locations.theme.Dimens

@Composable
fun PermissionDialog(dialogData: DialogData, onAnswer: (DialogAnswerEvent) -> Unit = {}) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(25.dp),
            onDismissRequest = {
                openDialog.value = false
                onAnswer(DialogAnswerEvent.Dismiss)
            },
            title = {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = dialogData.title, fontSize = 18.sp)
                }
            }, buttons = {
                Column(modifier = Modifier.padding(Dimens.big)) {
                    if (dialogData.negativeText.isNotBlank()) {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                                onAnswer(DialogAnswerEvent.Negative)
                            }, modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = dialogData.negativeText)
                        }
                    }

                    if (dialogData.positiveText.isNotBlank()) {
                        Button(onClick = {
                            openDialog.value = false
                            onAnswer(DialogAnswerEvent.Positive)
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = dialogData.positiveText)
                        }
                    }
                }
            }
        )
    }
}

sealed interface DialogAnswerEvent {
    object Dismiss : DialogAnswerEvent
    object Positive : DialogAnswerEvent
    object Negative : DialogAnswerEvent
}

data class DialogData(
    val title: AnnotatedString,
    val message: AnnotatedString,
    val positiveText: String = "",
    val negativeText: String = ""
)
