package ru.zhogin.composeClientApp.compose.screens

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.TextFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.composeClientApp.dto.ColorType
import ru.zhogin.composeClientApp.dto.GenderType
import ru.zhogin.composeClientApp.ui.theme.MyGrey
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.Pink80
import ru.zhogin.composeClientApp.viewmodel.CalendarDayEventViewModel
import ru.zhogin.composeClientApp.viewmodel.CalendarDayViewModel
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

private val DayFormatter = DateTimeFormatter.ofPattern("EE, MMM d")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDayAndEventsScreen(
    calendarDayViewModel: CalendarDayViewModel = hiltViewModel(),
    calendarDayEventViewModel: CalendarDayEventViewModel = hiltViewModel(),
    clientViewModule: ClientViewModule = hiltViewModel(),
    onNavigateToSelectClientScreen: () -> Unit,
    onNavigationBack: () -> Unit,
) {
    val calendarDayDate = remember {
        mutableStateOf(calendarDayViewModel.editedSelectedDay.value)
    }
    val checkedStateWeekend = remember { mutableStateOf(false) }
    val checkedStateWorkingDay = remember { mutableStateOf(false) }
    val clientName = remember {
        mutableStateOf(clientViewModule.editedClient.value)
    }
    val description = remember {
        mutableStateOf("")
    }
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]
    val time = remember {
        mutableStateOf("")
    }
    val endTime = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            time.value =
                if (hour > 9 && minute > 9) {
                    "$hour:$minute"
                } else if (hour < 10 && minute < 10) {
                    "0$hour:0$minute"
                } else if (hour < 10 && minute > 9) {
                    "0$hour:$minute"
                } else "$hour:0$minute"
        }, hour, minute, true
    )
    val timePickerDialogEnd = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            endTime.value =
                if (hour > 9 && minute > 9) {
                    "$hour:$minute"
                } else if (hour < 10 && minute < 10) {
                    "0$hour:0$minute"
                } else if (hour < 10 && minute > 9) {
                    "0$hour:$minute"
                } else "$hour:0$minute"
        }, hour, minute, true
    )
    Column(
        modifier = Modifier.fillMaxSize().background(MyGrey),
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
                    .background(Pink80)
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                calendarDayDate.value?.format(DayFormatter)?.let {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Black,
                        fontSize = 26.sp,
                        //text = calendarDayDate.value.toString(),
                        text = it,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Row(modifier = Modifier.padding(top = 16.dp)) {
                    IconButton(
                        onClick = {
                            onNavigateToSelectClientScreen()
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Filled.AccountCircle, contentDescription = "Choose client",
                        )
                    }
                    Text(
                        color = Color.Black,
                        fontSize = 22.sp,
                        text = "${clientName.value?.surname}" + " " + "${clientName.value?.name}" + " " + "${clientName.value?.patronymicSurname}",
                    )

                }
                Row(modifier = Modifier.padding(top = 16.dp)) {
                    IconButton(
                        onClick = { timePickerDialog.show() },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Filled.AddCircle, contentDescription = "Start Time",
                        )
                    }
                    Text(
                        color = Color.Black,
                        fontSize = 22.sp,
                        text = "Start time: ${time.value}",
                    )

                }
                Row(modifier = Modifier.padding(top = 16.dp)) {
                    IconButton(
                        onClick = { timePickerDialogEnd.show() },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Filled.AddCircle, contentDescription = "End Time",
                        )
                    }
                    Text(
                        color = Color.Black,
                        fontSize = 22.sp,
                        text = "End time: ${endTime.value}",
                    )

                }
                TextField(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .background(Pink80),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,

                        ),
                    label = {
                        Text(text = "Description")
                    },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Pink80),
                    value = description.value,
                    onValueChange = {
                        description.value = it
                    })


                Row {
                    Checkbox(
                        checked = checkedStateWorkingDay.value,
                        onCheckedChange = { checkedStateWorkingDay.value = it },
                        modifier = Modifier.padding(top = 16.dp,)
                    )
                    Text(
                        "Working day", fontSize = 22.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 23.dp)
                    )
                }

                Row {
                    Checkbox(
                        checked = checkedStateWeekend.value,
                        onCheckedChange = { checkedStateWeekend.value = it },
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        "Weekend", fontSize = 22.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 23.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    FloatingActionButton(
                        onClick = {
                            if (!checkedStateWeekend.value) {


                                calendarDayViewModel.editedDay.value =
                                    calendarDayViewModel.editedDay.value?.copy(
                                        isWeekend = checkedStateWeekend.value,
                                        isWorkingDay = checkedStateWorkingDay.value,
                                        date = calendarDayDate.value.toString()
                                    )
                                calendarDayViewModel.saveDay()
                                val starTime = "${calendarDayDate.value}T" + time.value + ":00"
                                val endedTime = "${calendarDayDate.value}T" + endTime.value + ":00"
                                calendarDayEventViewModel.editedDayEvent.value =
                                    calendarDayDate.value?.let {
                                        clientViewModule.editedClient.value?.id?.let { client ->
                                            calendarDayEventViewModel.editedDayEvent.value?.copy(
                                                date = it,
                                                name = "${clientName.value?.surname}" + " " + "${clientName.value?.name}" + " " + "${clientName.value?.patronymicSurname}",
                                                start = LocalDateTime.parse(starTime),
                                                end = LocalDateTime.parse(endedTime),
                                                description = description.value,
                                                color = if (clientViewModule.editedClient.value?.gender == GenderType.MALE) ColorType.WHITE
                                                else ColorType.GREEN,
                                                clientId = client


                                            )
                                        }
                                    }
                                calendarDayEventViewModel.saveDayEvent()



                                calendarDayViewModel.clearData()
                                calendarDayEventViewModel.clearData()
                                clientViewModule.clearData()
                                onNavigationBack()
                            } else {
                                calendarDayViewModel.editedDay.value =
                                    calendarDayViewModel.editedDay.value?.copy(
                                        isWeekend = checkedStateWeekend.value,
                                        isWorkingDay = checkedStateWorkingDay.value,
                                        date = calendarDayDate.value.toString()
                                    )
                                calendarDayViewModel.saveDay()
                                calendarDayViewModel.clearData()
                                calendarDayEventViewModel.clearData()
                                clientViewModule.clearData()
                                onNavigationBack()
                            }
                        },
                        backgroundColor = Orange,

                        ) {
                        Icon(Icons.Filled.Done, contentDescription = "Done")
                    }
                }
            }
        }

    }
}