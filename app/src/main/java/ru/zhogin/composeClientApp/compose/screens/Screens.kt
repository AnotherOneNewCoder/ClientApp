package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ru.zhogin.composeClientApp.R


@Composable
fun NotesScreen() {
    Image(
        painter = painterResource(
            id = R.drawable.cat_profile
        ),
        contentDescription = "img3",
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
}

@Composable
fun SettingScreen() {
    Image(
        painter = painterResource(
            id = R.drawable.set_cat
        ),
        contentDescription = "img4",
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
}