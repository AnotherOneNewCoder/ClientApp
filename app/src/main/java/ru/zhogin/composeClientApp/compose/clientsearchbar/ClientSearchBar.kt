package ru.zhogin.composeClientApp.compose.clientsearchbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.ui.theme.Brize2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientSearchBar() {
    val searchText = remember {
        mutableStateOf("")
    }
//    val isActive = remember {
//        mutableStateOf(false)
//    }
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

        ,
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
}