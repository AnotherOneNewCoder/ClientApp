package ru.zhogin.composeClientApp.compose.client

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.PinkTrans
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.ui.theme.Brize
import ru.zhogin.composeClientApp.ui.theme.MyTransperent
import ru.zhogin.composeClientApp.ui.theme.Purple40


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ClientListItem(client: Client) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MyTransperent)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(1.dp, Purple40)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Brize)

        ) {
            GlideImage(
                model = client.photo, contentDescription = "Client's avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(4.dp)
                    .size(64.dp)
                    .clip(CircleShape)

            ) {
                it.error(R.drawable.belka)
                    .placeholder(R.drawable.belka)
            }
//                Image(painter = painterResource(id = R.drawable.belka), contentDescription = "Client's avatar",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .padding(4.dp)
//                        .size(80.dp)
//                        .clip(CircleShape)
//                        .background(Color.Transparent)
//                )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp),
                //.background(Color.Transparent),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = client.name,
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
        }

    }
}