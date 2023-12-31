package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
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
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.client.SelectClientListItem
import ru.zhogin.composeClientApp.ui.theme.Brize2

import ru.zhogin.composeClientApp.ui.theme.PinkTrans
import ru.zhogin.composeClientApp.ui.theme.PurpleGrey40
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectUserScreen(
    clientViewModule: ClientViewModule = hiltViewModel(),
    onNavigateUp: () -> Unit,

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


    Surface(modifier = Modifier.fillMaxSize()) {


        Box(modifier = with(Modifier) {
            fillMaxSize()
                .background(PurpleGrey40)

        }) {
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

                    ,
                    onActiveChange = {

                    }) {

                }
                LazyColumn(
                    modifier = Modifier
                        .background(PinkTrans)
                        .padding(bottom = 8.dp)
                ) {
                    if (searchText.value.isEmpty()) {
                        items(listClient.value) { client ->
                            SelectClientListItem(client = client,
                                onNavigateUp = {
                                    clientViewModule.editedClient.value = client
                                    onNavigateUp()
                                               },

                            )
                        }
                    } else {
                        items(searchListClient.value) { searchClient ->
                            SelectClientListItem(client = searchClient, onNavigateUp = {
                                clientViewModule.editedClient.value = searchClient
                                onNavigateUp()
                            })
                        }
                    }
                }
            }



        }

    }

}