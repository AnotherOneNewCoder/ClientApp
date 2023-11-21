package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.client.ClientListItem
import ru.zhogin.composeClientApp.compose.clientsearchbar.ClientSearchBar
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.PinkTrans


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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen() {

    val client = Client(
        id = 1L,
        telNumber = "+79265850777",
        photo = "https://avatars.mds.yandex.net/i?id=16c04de0d36ff0c7e7f604d46d19235bfb93df43-8497429-images-thumbs&n=13",
        name = "Anastasia Byzmakova",
        dateOfBirth = "07.04.1980"
    )


    Box(modifier = with(Modifier) {
        fillMaxSize()
            .background(Color.Black)
            .paint(
                painterResource(id = R.drawable.full_cat),
                contentScale = ContentScale.Crop
            )
    }) {
        
        Column(
            modifier = Modifier.background(PinkTrans)
        ) {
            ClientSearchBar()
            ClientListItem(client = client)
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp, end = 16.dp)
        , contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(onClick = { /*TODO*/ },
                backgroundColor = Orange,

                ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }

    }

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