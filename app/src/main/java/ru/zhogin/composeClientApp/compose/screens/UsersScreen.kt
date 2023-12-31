package ru.zhogin.composeClientApp.compose.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.bottomnav.BottomNavigationView
import ru.zhogin.composeClientApp.compose.client.ClientListItem
import ru.zhogin.composeClientApp.ui.theme.Brize2
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.PinkTrans
import ru.zhogin.composeClientApp.ui.theme.PurpleGrey40
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    clientViewModule: ClientViewModule = hiltViewModel(),
    onNavigationNewClient: () -> Unit,
    onNavigationAvatarFullSize: () -> Unit,
    onNavigationEditClient: () -> Unit,
    onNavigationClientFullInfoScreen: () -> Unit,
    ) {


    val listClient = clientViewModule.data.collectAsState(initial = emptyList())


    val searchText = remember {
        mutableStateOf("")
    }
    val searchListClient =
        clientViewModule.searchClient(searchText.value).collectAsState(initial = emptyList())

//    var uri: String by remember {
//        mutableStateOf("")
//    }
    val navController = rememberNavController()

    Scaffold(
        floatingActionButtonPosition = androidx.compose.material.FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    clientViewModule.clearData()
                    onNavigationNewClient()
                },
                backgroundColor = Orange,

                ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        backgroundColor = PurpleGrey40,
        bottomBar = { BottomNavigationView(navController = navController) }

    ) {


//        Box(modifier = with(Modifier) {
//            fillMaxSize()
//                .background(Color.Black)
//                .paint(
//                    painterResource(id = R.drawable.full_cat),
//                    contentScale = ContentScale.Crop
//                )
//        }) {
            Column {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = SearchBarDefaults.colors(
                        containerColor = Brize2,
                        inputFieldColors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White
                        )
                    ),

                    query = searchText.value,
                    onQueryChange = { text ->
                        searchText.value = text
                    },
                    onSearch = { text ->
                        searchText.value = text
                        //isActive.value = false
                    },
                    placeholder = {
                        Text(
                            fontSize = 20.sp,
                            color = Color.White,
                            text = stringResource(id = R.string.search),
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    },
                    active = false
                    //isActive.value
                    ,
                    onActiveChange = {
                        //isActive.value = it
                    }) {

                }
                LazyColumn(
                    modifier = Modifier
                        .background(PinkTrans)
                        .padding(bottom = 108.dp)
                ) {
                    if (searchText.value.isEmpty()) {
                        items(listClient.value) { client ->
                            ClientListItem(client = client, onClick = {
                                clientViewModule.setPhoto(client.photo?.toUri())
                                Log.d("MyLog", "Uri: ${clientViewModule.photo.value}")
                                onNavigationAvatarFullSize()
                            },
                                onClickEdit = {
                                    clientViewModule.editedClient.value =
                                        clientViewModule.editedClient.value?.copy(
                                            id = client.id,
                                            telNumber = client.telNumber,
                                            photo = client.photo,
                                            name = client.name,
                                            surname = client.surname,
                                            patronymicSurname = client.patronymicSurname,
                                            dateOfBirth = client.dateOfBirth,
                                            gender = client.gender,
                                            visits = client.visits,
                                            works = client.works,
                                            prices = client.prices,
                                            tips = client.tips,
                                            notes = client.notes,
                                            durations = client.durations,

                                        )

                                    onNavigationEditClient()


                                },
                                onLongClickClientName = {
                                    clientViewModule.editedClient.value = client
                                    onNavigationClientFullInfoScreen()
                                }

                            )
                        }
                    } else {
                        items(searchListClient.value) { searchClient ->
                            ClientListItem(client = searchClient, onClick = {
                                clientViewModule.setPhoto(searchClient.photo?.toUri())
                                onNavigationAvatarFullSize()
                            },
                                onClickEdit = {
                                    clientViewModule.editedClient.value =
                                        clientViewModule.editedClient.value?.copy(
                                            id = searchClient.id,
                                            telNumber = searchClient.telNumber,
                                            photo = searchClient.photo,
                                            name = searchClient.name,
                                            surname = searchClient.surname,
                                            patronymicSurname = searchClient.patronymicSurname,
                                            dateOfBirth = searchClient.dateOfBirth,
                                            gender = searchClient.gender,
                                            visits = searchClient.visits,
                                            works = searchClient.works,
                                            prices = searchClient.prices,
                                            tips = searchClient.tips,
                                            notes = searchClient.notes,
                                            durations = searchClient.durations,
                                        )

                                    onNavigationEditClient(

                                    )


                                },
                                onLongClickClientName = {
                                    clientViewModule.editedClient.value = searchClient
                                    onNavigationClientFullInfoScreen()
                                }
                            )
                        }
                    }
               // }
            }


//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(bottom = 64.dp, end = 16.dp),
//                contentAlignment = Alignment.BottomEnd
//            ) {
//
//            }
        }

    }

}

