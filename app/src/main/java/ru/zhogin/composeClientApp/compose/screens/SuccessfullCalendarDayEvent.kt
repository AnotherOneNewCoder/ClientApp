package ru.zhogin.composeClientApp.compose.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.dto.GenderType
import ru.zhogin.composeClientApp.services.MyUtils
import ru.zhogin.composeClientApp.ui.theme.MyBlue
import ru.zhogin.composeClientApp.ui.theme.MyPink
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.PurpleGrey40
import ru.zhogin.composeClientApp.viewmodel.CalendarDayEventViewModel
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule
import java.time.format.DateTimeFormatter

private val DayFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SuccessfulCalendarDayEvent(
    calendarDayEventViewModel: CalendarDayEventViewModel = hiltViewModel(),
    clientViewModule: ClientViewModule = hiltViewModel(),
    onNavigateToCalendarScreen: () -> Unit,
) {
    val id = calendarDayEventViewModel.editedDayEvent.value?.clientId

//    val client = id?.let { clientViewModule.getClientById(it) }?.collectAsState(initial = clientViewModule.editedClient.value)
    val client = id?.let { clientViewModule.getClientById(it) }

//    val clientName =
//        "${client?.value?.surname}" + " " + "${client?.value?.name}" + " " + "${client?.value?.patronymicSurname}"

    val clientName =
        "${client?.surname}" + " " + "${client?.name}" + " " + "${client?.patronymicSurname}"

//    val start = calendarDayEventViewModel.editedDayEvent.value?.start
//    val end = calendarDayEventViewModel.editedDayEvent.value?.end
//
//    val calculateDuration = ChronoUnit.MINUTES.between(start, end)
//    val df = DecimalFormat("#.#")
    //val hours = df.format(calculateDuration.toDouble() / 60)
//    val hours = df.format(MyUtils.durationToHour(calculateDuration))
    val date = calendarDayEventViewModel.editedDayEvent.value?.date?.format(DayFormatter).toString()

//    val duration = remember {
//        mutableStateOf(hours)
//    }
    val typeOfWork = remember {
        mutableStateOf("")
    }
    val price = remember {
        mutableStateOf("")
    }
    val tips = remember {
        mutableStateOf("")
    }
    val note = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    //val listDuration = clientViewModule.editedClient.value?.durations
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Orange,
                onClick = {
                    price.value.ifBlank {
                        Toast.makeText(
                            context,
                            R.string.field_price_should_be_filled,
                            Toast.LENGTH_SHORT
                        ).show()
                        return@FloatingActionButton
                    }

                    calendarDayEventViewModel.editedDayEvent.value?.id?.let {
                        calendarDayEventViewModel.setDone(
                            it
                        )
                    }
//                            val addDuration = MyUtils.durationToMinute(duration.value.toDouble())
//                            val newListDuration = listDuration?.plus(addDuration)
                    clientViewModule.editedClient.value =
                        clientViewModule.editedClient.value?.let {
//                                   newListDuration?.let { it1 ->
                            clientViewModule.editedClient.value?.copy(
                                visits = it.visits.plus(date),
                                works = it.works.plus(typeOfWork.value.ifBlank { "-" }),
                                //durations = it.durations.plus(MyUtils.durationToMinute(duration.value.toDouble())),
                                durations = it.durations.plus(0L),
                                prices = it.prices.plus(MyUtils.priceToLong(price.value.toDouble())),
                                notes = it.notes.plus(note.value.ifBlank { "-" }),
                                tips = it.tips.plus(
                                    if (tips.value.isNotBlank()) MyUtils.priceToLong(tips.value.toDouble())
                                    else 0L
                                )
                            )
//                                   }
                        }



                    clientViewModule.saveClient()
                    Toast.makeText(context, R.string.event_created, Toast.LENGTH_SHORT).show()
                    calendarDayEventViewModel.clearData()
                    clientViewModule.clearData()
                    onNavigateToCalendarScreen()
                }
            ) {
                Icon(Icons.Filled.Done, contentDescription = "Done")


            }

        },
        backgroundColor = PurpleGrey40,
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                //.background(MyGrey)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Card(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(8.dp)

                    .verticalScroll(rememberScrollState()),
                border = BorderStroke(1.dp, Color.Black),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (clientViewModule.editedClient.value?.gender == GenderType.MALE) MyBlue
                            else MyPink
                        )
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Black,
                        fontSize = 26.sp,
                        //text = calendarDayDate.value.toString(),
                        text = clientName,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Black,
                        fontSize = 26.sp,
                        //text = calendarDayDate.value.toString(),
                        text = date,

                        )
                    OutlinedTextField(
                        value = typeOfWork.value,
                        onValueChange = { typeOfWork.value = it },
                        label = { Text(text = stringResource(id = R.string.type_of_work),
                            color = Color.Black,) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                        ),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 22.sp,
                        ),
                    )


//                OutlinedTextField(
//                    value = duration.value,
//                    onValueChange = { duration.value = it },
//                    label = { Text(text = stringResource(id = R.string.duration)) },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
//                )

                    OutlinedTextField(
                        value = price.value,
                        onValueChange = { price.value = it },
                        label = { Text(text = stringResource(id = R.string.price),
                            color = Color.Black,) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                        ),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 22.sp,
                        ),
                    )
                    OutlinedTextField(
                        value = tips.value,
                        onValueChange = { tips.value = it },
                        label = { Text(text = stringResource(id = R.string.tips),
                            color = Color.Black,) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                        ),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 22.sp,
                        ),
                    )

                    OutlinedTextField(
                        value = note.value,
                        onValueChange = { note.value = it },
                        label = { Text(text = stringResource(id = R.string.note),
                            color = Color.Black,) },
                        modifier = Modifier.padding(bottom = 32.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                        ),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 22.sp,
                        ),
                    )


                }


            }
        }
    }
}