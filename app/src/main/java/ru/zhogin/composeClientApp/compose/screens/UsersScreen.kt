package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.client.ClientListItem
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.ui.theme.Brize2
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    clientViewModule: ClientViewModule = hiltViewModel(),
    // to use viewModel only in navGraph
    clientsList: List<Client>,
    onNavigationNewClient: () -> Unit,
    onNavigationAvatarFullSize: (String) -> Unit,
    onNavigationEditClient: () -> Unit,
    onNavigationClientFullInfoScreen: () -> Unit,
) {


    //val listClient = clientViewModule.data.collectAsState(initial = emptyList())


    val searchText = remember {
        mutableStateOf("")
    }
    val searchListClient =
        clientViewModule.searchClient(searchText.value).collectAsState(initial = emptyList())
    var isSearchActive by remember {
        mutableStateOf(false)
    }


    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier.background(Color.White)
    ) {


        Column {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
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
                    isSearchActive = false
                },
                placeholder = {
                    Text(
                        fontSize = 20.sp,
                        color = Color.White,
                        text = stringResource(id = R.string.search),
                        //modifier = Modifier.padding(bottom = 12.dp)
                    )
                },
                active = false
                //isActive.value
                ,
                onActiveChange = {
                    isSearchActive = it
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            //future impl
                        })
                },
                trailingIcon = if (searchText.value.isNotEmpty()) {
                    {
                        IconButton(onClick = {  searchText.value = "" }) {
                            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close",tint = Color.White,)
                        }
                    }
                } else null
            ) {

            }
            LazyColumn(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp)
            ) {
                if (searchText.value.isEmpty()) {
                    items(clientsList) { client ->
                        ClientListItem(client = client, onClick = {
                            //clientViewModule.setPhoto(client.photo?.toUri())
                            onNavigationAvatarFullSize(
                                client.photo ?: ""
                            )
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
                            },
                            onRemoveClientById = {
                                clientViewModule.removeClientById(it)
                            }

                        )
                    }
                } else {
                    items(searchListClient.value) { searchClient ->
                        ClientListItem(client = searchClient, onClick = {
                            clientViewModule.setPhoto(searchClient.photo?.toUri())
                            onNavigationAvatarFullSize(searchClient.photo ?: "")
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
                            },
                            onRemoveClientById = {
                                clientViewModule.removeClientById(it)
                            }
                        )
                    }
                }
                // }
            }


        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 8.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                clientViewModule.clearData()
                onNavigationNewClient()
            },
            backgroundColor = Brize2,

            ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
}

