package ru.zhogin.composeClientApp.compose.alertdialog


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ru.zhogin.composeClientApp.R


@Composable
fun MyAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String
) {


    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                androidx.compose.material3.Icon(
                    Icons.Filled.Delete, contentDescription = "Delete",
                )
                Text(
                    text = dialogTitle,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
            }

        },
        text = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = dialogText,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
            }

        },
        confirmButton = {
            TextButton(
                onClick = { onConfirmation() }
            ) {
                Text(text = stringResource(id = R.string.yes),
                    color = MaterialTheme.colorScheme.onBackground)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = stringResource(id = R.string.no),
                    color = MaterialTheme.colorScheme.onBackground)
            }
        },
        backgroundColor = MaterialTheme.colorScheme.errorContainer


    )
}

@Preview
@Composable
fun MyAlertDialog_Preview() {
    val title = "Delete"
    val text = "Are you sure?"
    MyAlertDialog(
        onDismissRequest = {},
        onConfirmation = {},
        dialogTitle = title,
        dialogText = text,
    )
}