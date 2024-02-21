package ru.zhogin.composeClientApp.compose.alertdialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.ui.theme.Brize2

@Composable
fun DeleteDialogBox(
    //negativeButtonColor: Color = Color(0xFF35898F),
    negativeButtonColor: Color = Brize2,
    positiveButtonColor: Color = Color(0xFFFF0000),
    spaceBetweenElements: Dp = 18.dp,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    modifier: Modifier,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier.fillMaxWidth(0.92f),
            color = Color.Transparent
        ) {
            Box(
                modifier.fillMaxWidth()
            ) {
                // text and buttons
                Column(
                    modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(percent = 10)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier.height(36.dp))
                    Text(
                        text = dialogTitle,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier.height(spaceBetweenElements))
                    Text(
                        text = dialogText,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = modifier.padding(horizontal = 16.dp),
                        color = Color.Black
                    )
                    Spacer(modifier.height(spaceBetweenElements))

                    // buttons
                    Row(
                        modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        DialogButton(buttonColor = negativeButtonColor, buttonText = stringResource(
                            id = R.string.no
                        )) {
                            onDismissRequest()
                        }
                        DialogButton(buttonColor = positiveButtonColor, buttonText = stringResource(
                            id = R.string.yes
                        )) {
                            onConfirmation()
                        }
                    }
                    Spacer(modifier.height(spaceBetweenElements * 2))
                }
                Icon(
                    Icons.Filled.Delete, contentDescription = "Delete", tint = positiveButtonColor,
                    modifier = modifier.background(
                        color = Color.White,
                        shape = CircleShape,
                    )
                        .border(width = 2.dp, shape = CircleShape, color = positiveButtonColor)
                        .padding(16.dp)
                        .align(alignment = Alignment.TopCenter)
                )
            }
        }

    }

}

@Composable
fun DialogButton(
    cornerRadiusPercent: Int = 26,
    buttonColor: Color,
    buttonText: String,
    onDismiss: () -> Unit,
){
    Box(
        modifier = Modifier
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(cornerRadiusPercent),
            )
            .clickable { onDismiss() }
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
