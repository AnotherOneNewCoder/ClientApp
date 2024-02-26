package ru.zhogin.composeClientApp.compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zhogin.composeClientApp.ui.theme.Brize2

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowClientNotes(
    text: String,
    onNavigationBack: (String) -> Unit,
) {
    var changeText by rememberSaveable {
        mutableStateOf(false)
    }
    var mutableText by rememberSaveable {
        mutableStateOf(text)
    }
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = {
                        changeText = !changeText
                    },
                    backgroundColor = Brize2,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    if (changeText) Icon(Icons.Filled.Done, contentDescription = "Done", tint = Color.White)
                    else Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = Color.White)
                }

                FloatingActionButton(
                    onClick = {
                        onNavigationBack(mutableText)
                    },
                    backgroundColor = Brize2,
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            }

        },
        backgroundColor = Color.White,
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .padding(8.dp)

                .fillMaxSize(),
            //border = BorderStroke(1.dp, Color.Black)

        ) {
            if (!changeText) {
                Text(
                    text = mutableText,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(14.dp),
                    color = Color.Black
                )
            } else {
                TextField(
                    value = mutableText, onValueChange = { mutableText = it },
                    textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
                )
            }
        }
    }
}