package ru.zhogin.composeClientApp.compose.screens

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.dto.ColorType
import ru.zhogin.composeClientApp.dto.GenderType
import ru.zhogin.composeClientApp.ui.theme.Brize
import ru.zhogin.composeClientApp.ui.theme.Brize3
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.PurpleGrey40
import ru.zhogin.composeClientApp.viewmodel.CalendarDayEventViewModel
import ru.zhogin.composeClientApp.viewmodel.CalendarDayViewModel
import ru.zhogin.composeClientApp.viewmodel.ClientViewModule
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

//private val DayFormatter = DateTimeFormatter.ofPattern("EE, MMM d")
private val DayFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
    val fullName =
        "${clientName.value?.surname}" + " " + "${clientName.value?.name}" + " " + "${clientName.value?.patronymicSurname}"
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
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (!checkedStateWeekend.value) {

                        if (clientName.value == null || time.value.isBlank() || endTime.value.isBlank()) {
                            Toast.makeText(
                                context,
                                R.string.choose_client,
                                Toast.LENGTH_SHORT
                            ).show()
                            return@FloatingActionButton
                        }
                        calendarDayViewModel.editedDay.value =
                            calendarDayViewModel.editedDay.value?.copy(
                                isWeekend = checkedStateWeekend.value,
                                isWorkingDay = checkedStateWorkingDay.value,
                                date = calendarDayDate.value.toString()
                            )
                        calendarDayViewModel.saveDay()
                        val starTime = "${calendarDayDate.value}T" + time.value + ":00"
                        val endedTime =
                            "${calendarDayDate.value}T" + endTime.value + ":00"
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
        },
        backgroundColor = PurpleGrey40,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {


            Card(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState()),
                border = BorderStroke(1.dp, Color.Black)

            ) {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brize)
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
                        TextButton(
                            onClick = { onNavigateToSelectClientScreen() },
                            enabled = true,
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(containerColor = Orange)

                        ) {
                            Text(
                                text = stringResource(id = R.string.client),
                                color = Color.Black,
                                fontSize = 22.sp,
                            )
                        }

                        Text(
                            modifier = Modifier.padding(top = 10.dp, start = 4.dp),
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            text = fullName,
                        )

                    }
                    Row(modifier = Modifier.padding(top = 16.dp)) {
                        TextButton(
                            onClick = { timePickerDialog.show() },
                            enabled = true,
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(containerColor = Orange)

                        ) {
                            Text(
                                text = stringResource(id = R.string.start_time),
                                color = Color.Black,
                                fontSize = 22.sp,
                            )
                        }
                        Text(
                            modifier = Modifier.padding(top = 10.dp, start = 4.dp),
                            color = Color.Black,
                            fontSize = 22.sp,
                            text = time.value,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {

                        TextButton(
                            onClick = { timePickerDialogEnd.show() },
                            enabled = true,
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(containerColor = Orange)

                        ) {
                            Text(
                                text = stringResource(id = R.string.end_time),
                                color = Color.Black,
                                fontSize = 22.sp,
                            )
                        }
                        Text(
                            modifier = Modifier.padding(top = 10.dp, start = 4.dp),
                            color = Color.Black,
                            fontSize = 22.sp,
                            text = endTime.value,
                            fontWeight = FontWeight.Bold,
                        )

                    }
                    OutlinedTextField(
                        value = description.value,
                        onValueChange = { description.value = it },
                        label = {
                            Text(
                                text = stringResource(id = R.string.description),
                                fontSize = 22.sp,
                                color = Color.Black,
                            )
                        },

                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Brize3,
                            focusedBorderColor = Orange,
                            unfocusedBorderColor = Orange,
                            cursorColor = Orange,
                        ),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 22.sp,
                        ),

                        )

                    Row {
                        Checkbox(
                            checked = checkedStateWorkingDay.value,
                            onCheckedChange = {
                                if (checkedStateWeekend.value) checkedStateWorkingDay.value = false
                                else checkedStateWorkingDay.value = it
                            },
                            modifier = Modifier.padding(top = 16.dp),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Orange,
                                checkmarkColor = Color.Black
                            )
                        )
                        Text(
                            stringResource(id = R.string.working_day), fontSize = 22.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 23.dp)
                        )
                    }

                    Row {
                        Checkbox(
                            checked = checkedStateWeekend.value,
                            onCheckedChange = {
                                if (checkedStateWorkingDay.value) checkedStateWeekend.value = false
                                else checkedStateWeekend.value = it
                            },
                            modifier = Modifier.padding(top = 16.dp),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Orange,
                                checkmarkColor = Color.Black
                            )
                        )
                        Text(
                            stringResource(id = R.string.weekend), fontSize = 22.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 23.dp, bottom = 32.dp)
                        )
                    }

                }
            }
        }
    }
}