package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.client.ClientListItem
import ru.zhogin.composeClientApp.compose.clientsearchbar.ClientSearchBar
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.dto.GenderType
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.PinkTrans
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule


@Composable
fun CalendarScreen() {
    Image(
        painter = painterResource(
            id = R.drawable.cat_profile
        ),
        contentDescription = "img1",
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
}




@Composable
fun NotesScreen() {
    Image(
        painter = painterResource(
            id = R.drawable.cat_face
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