package ru.zhogin.composeClientApp.compose.screens


import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.dto.GenderType
import ru.zhogin.composeClientApp.ui.theme.Brize2


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewClientScreen(
    onNavigation: (client: Client) -> Unit,
    client: Client,
) {

    val clientName = rememberSaveable {
        mutableStateOf(client.name)
    }

    val clientSurname = rememberSaveable {
        mutableStateOf(client.surname)
    }

    val clientPatronymicSurname = rememberSaveable {
        mutableStateOf(client.patronymicSurname)
    }

    val clientPhone = rememberSaveable {
        mutableStateOf(client.telNumber)
    }

    val clientDateOfBirth = rememberSaveable {
        mutableStateOf(client.dateOfBirth)
    }
    val checkedSwitcher = rememberSaveable {
        mutableStateOf(false)
    }


    val imageUri = rememberSaveable {
        mutableStateOf(client.photo)
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            imageUri.value = it.toString()
        }
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier.background(Color.White)
            .padding(4.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                modifier = Modifier
                    .size(320.dp)
                    .padding(top = 8.dp)
                    .clip(CircleShape)
                    .clickable {
                        launcher.launch("image/*")
                    },
                model = imageUri.value,

                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
            ) {
                it.error(R.drawable.no_avatar)
                    .placeholder(R.drawable.no_avatar)
            }



            TextField(
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp
                ),
                label = {
                    Text(text = stringResource(id = R.string.name))
                },
                value = clientName.value,
                onValueChange = {
                    clientName.value = it
                },
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Brize2,
                    focusedPlaceholderColor = Brize2,
                    focusedIndicatorColor = Brize2,
                    cursorColor = Brize2,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                ))

            TextField(
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp
                ),
                label = {
                    Text(text = stringResource(id = R.string.surname))
                },
                value = clientSurname.value,
                onValueChange = {
                    clientSurname.value = it
                },
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Brize2,
                    focusedPlaceholderColor = Brize2,
                    focusedIndicatorColor = Brize2,
                    cursorColor = Brize2,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                ))

            TextField(
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp
                ),
                label = {
                    Text(text = stringResource(id = R.string.patronymic_surname))
                },
                value = clientPatronymicSurname.value.toString(),
                onValueChange = {
                    clientPatronymicSurname.value = it
                },
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Brize2,
                    focusedPlaceholderColor = Brize2,
                    focusedIndicatorColor = Brize2,
                    cursorColor = Brize2,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                ))


            TextField(
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp
                ),
                value = clientPhone.value,
                onValueChange = {
                    clientPhone.value = it
                },
                label = {
                    Text(text = stringResource(id = R.string.phone))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Brize2,
                    focusedPlaceholderColor = Brize2,
                    focusedIndicatorColor = Brize2,
                    cursorColor = Brize2,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                )
            )
            TextField(
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp
                ),
                value = clientDateOfBirth.value.toString()

                ,
                onValueChange = {
                    clientDateOfBirth.value = it
                },
                label = {
                    Text(text = stringResource(id = R.string.date_of_birth))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Brize2,
                    focusedPlaceholderColor = Brize2,
                    focusedIndicatorColor = Brize2,
                    cursorColor = Brize2,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                )
            )
            Row {
                Text(
                    text = stringResource(id = R.string.female),
                    modifier = Modifier.padding(top = 10.dp, end = 8.dp),
                    fontSize = 16.sp,
                )
                Switch(checked = checkedSwitcher.value, onCheckedChange = {
                    checkedSwitcher.value = it
                },
                    colors = SwitchDefaults.colors(
                        checkedBorderColor = Brize2,
                        checkedIconColor = Color.White,
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Brize2,
                    )
                    )
                Text(
                    text = stringResource(id = R.string.male),
                    modifier = Modifier.padding(start = 8.dp, top = 10.dp),
                    fontSize = 16.sp,
                )
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
                    if (clientName.value.isNotBlank() && clientSurname.value.isNotBlank()) {
                        val newClient = Client(
                            id = client.id,
                            name = clientName.value,
                            surname = clientSurname.value,
                            patronymicSurname = clientPatronymicSurname.value.toString(),
                            telNumber = clientPhone.value,
                            gender = when (checkedSwitcher.value) {
                                false -> GenderType.FEMALE
                                true -> GenderType.MALE

                            },
                            photo = imageUri.value.toString(),
                            dateOfBirth = clientDateOfBirth.value.toString(),
                            visits = client.visits,
                            works = client.works,
                            prices = client.prices,
                            tips = client.tips,
                            durations = client.durations,
                            notes = client.notes
                        )
                        Toast.makeText(context, R.string.client_added , Toast.LENGTH_SHORT).show()
                        onNavigation(newClient)
                    } else
                        Toast.makeText(context, R.string.fields_name_and_surname_should_be_filled , Toast.LENGTH_SHORT).show()
                },
                backgroundColor = Brize2,

                ) {
                Icon(Icons.Filled.Done, contentDescription = "Add", tint = Color.White)
            }
        }
    }



}


