package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    ) {


    GlideImage(
        model = clientViewModule.photo.value?.uri, contentDescription = "Client's avatar",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxSize().background(Color.Black)
            .clickable {


                onNavigationUsersScreen()
                //clientViewModule.clearPhoto()
            }

    ) {
        it.error(R.drawable.no_avatar)
            .placeholder(R.drawable.no_avatar)
    }
}