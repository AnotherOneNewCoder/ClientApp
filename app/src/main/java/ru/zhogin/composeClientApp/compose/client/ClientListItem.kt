package ru.zhogin.composeClientApp.compose.client

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.alertdialog.DeleteDialogBox
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.dto.GenderType


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun ClientListItem(
    client: Client,
    onClick: () -> Unit,
    onClickEdit: () -> Unit,
    onLongClickClientName: () -> Unit,
    onRemoveClientById: (Long) -> Unit,

) {
    val fullNameText =
        if (!client.patronymicSurname.isNullOrBlank()) client.surname + " " + client.name + " " + client.patronymicSurname
        else client.surname + " " + client.name

    val openAlertDialog = remember {
        mutableStateOf(false)
    }
    when (openAlertDialog.value) {
        true -> {

            DeleteDialogBox(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = { onRemoveClientById(client.id)
                    openAlertDialog.value = false },
                dialogTitle = stringResource(id = R.string.delete) + "\n${fullNameText}",
                dialogText = stringResource(id = R.string.are_you_sure),
                modifier = Modifier,
            )
        }

        else -> {}
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)

                ,
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
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
                        .clickable {
                            onClick()
                        }
                ) {
                    it.error(R.drawable.no_avatar)
                        .placeholder(R.drawable.no_avatar)
                }
//                Box {
//                    Text(
//                        text = client.id.toString(),
//                        modifier = Modifier
//                            .padding(5.dp)
//                            .background(Color.White, shape = CircleShape)
//
//                    )
//                }

            }
            Column {
                IconButton(
                    onClick = { onClickEdit() },
                    modifier = Modifier.size(32.dp),

                    ) {
                    Icon(
                        Icons.Filled.Create, contentDescription = "Edit",

                        )
                }
                IconButton(
                    onClick = {
                        openAlertDialog.value = true
                    },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete",
                    )
                }
            }

            Column(
                modifier = Modifier.padding(start = 8.dp),
            ) {
                Text(

                    text = fullNameText,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth(0.95f)
                        .combinedClickable(
                            enabled = true,
                            onClick = { onLongClickClientName() },
                            onLongClick = { onLongClickClientName() }
                        )
                        .horizontalScroll(rememberScrollState())
                        .padding(bottom = 8.dp)
                    ,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = client.telNumber,
                    modifier = Modifier.background(Color.Transparent),
                    style = MaterialTheme.typography.bodyLarge
                )

            }

        }

    }
}

@Preview
@Composable
fun ClientListItem_Preview() {
    val client = Client(
        id = 22L,
        telNumber = "+79991120545",
        photo = null,
        name = "Ivan",
        surname = "Zhogin",
        patronymicSurname = null,
        dateOfBirth = null,
        gender = GenderType.MALE
    )
    ClientListItem(
        client = client,
        onClick = { },
        onClickEdit = {  },
        onLongClickClientName = {  },
        onRemoveClientById = {},
    )
}