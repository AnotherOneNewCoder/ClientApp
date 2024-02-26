package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.client.SelectClientListItem
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.ui.theme.Brize2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectUserScreen(
    listClient: List<Client>,
    onNavigateUp: (client: Client) -> Unit,

    ) {

    val searchText = rememberSaveable {
        mutableStateOf("")
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

                },
                placeholder = {
                    Text(
                        fontSize = 20.sp,
                        color = Color.White,
                        text = stringResource(id = R.string.search),

                        )
                },
                active = false,
                onActiveChange = {

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
                        IconButton(onClick = { searchText.value = "" })
                        {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Close",
                                tint = Color.White,
                            )
                        }
                    }
                } else null
            ) {

            }
            LazyColumn(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight()
                    .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                if (searchText.value.isEmpty()) {
                    items(listClient) { client ->
                        SelectClientListItem(
                            client = client,
                            onNavigateUp = {
                                onNavigateUp(client)
                            },

                            )
                    }
                } else {
                    val result = ArrayList<Client>()
                    result.clear()
                    for (client in listClient) {
                        if (client.name.lowercase().contains(searchText.value.lowercase())) {
                            result.add(client)
                        } else if (client.surname.lowercase()
                                .contains(searchText.value.lowercase())
                        ) {
                            result.add(client)
                        } else if (client.patronymicSurname?.lowercase()
                                ?.contains(searchText.value.lowercase()) == true
                        ) {
                            result.add(client)
                        } else if (client.telNumber.lowercase()
                                .contains(searchText.value.lowercase())
                        ) {
                            result.add(client)
                        }
                    }
                    items(result) { searchClient ->
                        SelectClientListItem(client = searchClient, onNavigateUp = {
                            onNavigateUp(searchClient)
                        })
                    }
                }
            }
        }


    }

}

