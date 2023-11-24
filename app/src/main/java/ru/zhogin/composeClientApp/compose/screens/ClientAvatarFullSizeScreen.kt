package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ClientAvatarFullSizeScreen(

    onNavigationUsersScreen: () -> Unit,
    clientViewModule: ClientViewModule = hiltViewModel(),

){

    val uri = remember {
        mutableStateOf(clientViewModule.photo.value?.uri)
    }
    GlideImage(
        model = clientViewModule.uri, contentDescription = "Client's avatar",
        contentScale = ContentScale.None,
        modifier = Modifier
            .fillMaxSize()
            .clickable {

                onNavigationUsersScreen()
            }

    ) {
        it.error(R.drawable.no_avatar)
            .placeholder(R.drawable.no_avatar)
    }
}