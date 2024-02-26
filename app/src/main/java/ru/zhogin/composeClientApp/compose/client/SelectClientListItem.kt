package ru.zhogin.composeClientApp.compose.client

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.ui.theme.Brize2


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SelectClientListItem(
    client: Client,
    onNavigateUp: () -> Unit,
) {

    val fullNameText =
        if (!client.patronymicSurname.isNullOrBlank()) client.name + " " + client.surname + " " + client.patronymicSurname
        else client.name + " " + client.surname

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()



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
                modifier = Modifier.padding(start = 8.dp),
            ) {
                Text(
                    text = fullNameText,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth(0.85f)
                        .horizontalScroll(rememberScrollState())
                        ,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = client.telNumber,
                    modifier = Modifier.background(Color.Transparent),
                    style = MaterialTheme.typography.bodyLarge,
                )

            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 4.dp, end = 12.dp)
            ) {
                IconButton(
                    onClick = {
                        onNavigateUp()
                              },
                    enabled = true,
                    modifier = Modifier.size(56.dp),

                    ) {
                    Icon(
                        Icons.Filled.CheckCircle, contentDescription = "Select",
                        tint = Brize2,
                        modifier = Modifier.size(28.dp)
                    )

                }
            }

        }
    }
}