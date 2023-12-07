package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.composeClientApp.dto.ColorType
import ru.zhogin.composeClientApp.dto.GenderType

import ru.zhogin.composeClientApp.ui.theme.MyBlue
import ru.zhogin.composeClientApp.ui.theme.MyGrey
import ru.zhogin.composeClientApp.ui.theme.MyPink
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.viewmodel.CalendarDayEventViewModel
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule
import java.text.DecimalFormat
import java.time.temporal.ChronoUnit

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

    val start = calendarDayEventViewModel.editedDayEvent.value?.start
    val end = calendarDayEventViewModel.editedDayEvent.value?.end

    val calculateDuration = ChronoUnit.MINUTES.between(start, end)
    val df = DecimalFormat("#.#")
    val hours = df.format(calculateDuration.toDouble() / 60)
    val date = calendarDayEventViewModel.editedDayEvent.value?.date.toString()

    val duration = remember {
        mutableStateOf(hours)
    }
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


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyGrey),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .padding(8.dp)

                .verticalScroll(rememberScrollState()),

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
                    label = { Text(text = "Type of work") }
                )


                OutlinedTextField(
                    value = duration.value,
                    onValueChange = { duration.value = it },
                    label = { Text(text = "Duration") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = price.value,
                    onValueChange = { price.value = it },
                    label = { Text(text = "Price") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                OutlinedTextField(
                    value = tips.value,
                    onValueChange = { tips.value = it },
                    label = { Text(text = "Tips") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                OutlinedTextField(
                    value = note.value,
                    onValueChange = { note.value = it },
                    label = { Text(text = "Note") }
                )
                clientViewModule.editedClient.value?.telNumber?.let {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Black,
                        fontSize = 26.sp,
                        //text = calendarDayDate.value.toString(),
                        text = it,

                        )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    FloatingActionButton(
                        backgroundColor = Orange,
                        onClick = {
                            calendarDayEventViewModel.editedDayEvent.value?.id?.let {
                                calendarDayEventViewModel.setDone(
                                    it
                                )
                            }
                            clientViewModule.editedClient.value =
                                clientViewModule.editedClient.value?.works?.let { work ->
                                    clientViewModule.editedClient.value?.visits?.let { visit ->
                                        clientViewModule.editedClient.value?.durations?.let { durations ->
                                            clientViewModule.editedClient.value?.prices?.let { prices ->
                                                clientViewModule.editedClient.value?.notes?.let {notes->
                                                    clientViewModule.editedClient.value?.tips?.let { listTips ->
                                                        clientViewModule.editedClient.value?.copy(
                                                            visits = visit.plus(
                                                                date
                                                            ),
                                                            works = work.plus(typeOfWork.value),
                                                            durations = durations.plus(duration.value.toDouble()),
                                                            prices = prices.plus(price.value.toDouble()),
                                                            notes = notes.plus(note.value),
                                                            tips = listTips.plus(tips.value.toDouble())
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            clientViewModule.saveClient()

                            calendarDayEventViewModel.clearData()
                            clientViewModule.clearData()
                            onNavigateToCalendarScreen()
                        }
                    ) {
                        Icon(Icons.Filled.Done, contentDescription = "Done")


                    }
                }


            }
        }
    }
}