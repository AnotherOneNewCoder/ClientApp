package ru.zhogin.composeClientApp.compose.client

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.ui.theme.Brize
import ru.zhogin.composeClientApp.ui.theme.MyTransperent
import ru.zhogin.composeClientApp.ui.theme.Purple40
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SelectClientListItem(
    client: Client,
    clientViewModule: ClientViewModule = hiltViewModel(),
    onNavigateUp: () -> Unit,
) {

    val fullNameText =
        if (!client.patronymicSurname.isNullOrBlank()) client.name + " " + client.surname + " " + client.patronymicSurname
        else client.name + " " + client.surname

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MyTransperent)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        //.horizontalScroll(rememberScrollState())
        ,
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(1.dp, Purple40)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(Brize)

        ) {
            Box(contentAlignment = Alignment.BottomEnd) {
                GlideImage(
                    model = client.photo, contentDescription = "Client's avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(64.dp)
                        .clip(CircleShape)


                ) {
                    it.error(R.drawable.no_avatar)
                        .placeholder(R.drawable.no_avatar)
                }


            }


            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = fullNameText,
                    modifier = Modifier.background(Color.Transparent),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = client.telNumber,
                    modifier = Modifier.background(Color.Transparent),
                    fontSize = 20.sp,
                )

            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(end = 12.dp)
            ) {
                IconButton(
                    onClick = {
                        onNavigateUp()
                              },
                    modifier = Modifier.size(64.dp),

                    ) {
                    Icon(
                        Icons.Filled.Done, contentDescription = "Select",
                    )

                }
            }

        }
    }
}