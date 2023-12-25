package ru.zhogin.composeClientApp.compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.Card
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.PurpleGrey40

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowClientNotes(
    text: String,
    onNavigationBack: () -> Unit,
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigationBack()
                },
                backgroundColor = Orange,
            ) {
                Icon(Icons.Filled.Done, contentDescription = "Add")
            }
        },
        backgroundColor = PurpleGrey40,
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .padding(8.dp)

                .fillMaxSize(),
            border = BorderStroke(1.dp, Color.Black)

        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                modifier = Modifier.padding(14.dp)
                )
        }
    }
}