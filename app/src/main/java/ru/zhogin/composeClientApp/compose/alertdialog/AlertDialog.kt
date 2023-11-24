package ru.zhogin.composeClientApp.compose.alertdialog


import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

import ru.zhogin.composeClientApp.R


@Composable
fun MyAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText:String
) {



        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            title = {
                Text(text = dialogTitle,
                    textAlign = TextAlign.Center)
            },
            text = {
                Text(text = dialogText)
            },
            confirmButton = {
                TextButton(
                    onClick = { onConfirmation() }
                ) {
                    Text(text = stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissRequest() }) {
                    Text(text = stringResource(id = R.string.no))
                }
            }

        )
    }
