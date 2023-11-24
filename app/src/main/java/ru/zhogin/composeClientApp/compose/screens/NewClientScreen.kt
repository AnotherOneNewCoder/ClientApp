package ru.zhogin.composeClientApp.compose.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.dto.Client
import ru.zhogin.composeClientApp.dto.GenderType
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewClientScreen(
    clientViewModule: ClientViewModule = hiltViewModel(),
    onNavigation: () -> Unit
) {
    val clientName = remember {
        mutableStateOf("")
    }
    val clientPhone = remember {
        mutableStateOf("")
    }
    val clientDateOfBirth = remember {
        mutableStateOf("")
    }
    val checkedSwitcher = remember {
        mutableStateOf(false)
    }
    val imageUri= remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            imageUri.value = it
        }

    Card(

        shape = RoundedCornerShape(24.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                modifier = Modifier
                    .size(350.dp)
                    .padding(top = 8.dp)
                    .clickable {
                        launcher.launch("image/*")
                    },
                model = imageUri.value,

                contentDescription = "Avatar"
            ) {
                it.error(R.drawable.no_avatar)
                    .placeholder(R.drawable.no_avatar)
            }



            TextField(
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp
                ),
                label = {
                    Text(text = "Name")
                },
                value = clientName.value,
                onValueChange = {
                    clientName.value = it
                })


            TextField(
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp
                ),
                value = clientPhone.value,
                onValueChange = {
                    clientPhone.value = it
                },
                label = {
                    Text(text = "Phone")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )
            TextField(
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp
                ),
                value = clientDateOfBirth.value,
                onValueChange = {
                    clientDateOfBirth.value = it
                },
                label = {
                    Text(text = "Date of Birth")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                )
            )
            Row {
                Text(
                    text = "Female",
                    modifier = Modifier.padding(top = 10.dp, end = 8.dp),
                    fontSize = 16.sp,
                )
                Switch(checked = checkedSwitcher.value, onCheckedChange = {
                    checkedSwitcher.value = it
                })
                Text(
                    text = "Male",
                    modifier = Modifier.padding(start = 8.dp, top = 10.dp),
                    fontSize = 16.sp,
                )
            }

            TextButton(onClick = {
                clientViewModule.editedClient.value = Client(
                    id = 0,
                    name = clientName.value,
                    telNumber = clientPhone.value,
                    gender = when (checkedSwitcher.value) {
                        false -> GenderType.FEMALE
                        else -> GenderType.MALE
                    },
                    photo = imageUri.value.toString()
                )
                clientViewModule.saveClient()
                onNavigation()
            }) {
                Text(
                    text = stringResource(id = R.string.add),
                    fontSize = 20.sp
                )

            }
        }
    }


}