package ru.zhogin.composeClientApp.compose.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.alertdialog.MyAlertDialog
import ru.zhogin.composeClientApp.compose.bottomnav.BottomNavigationView
import ru.zhogin.composeClientApp.compose.calendar.CustomDay
import ru.zhogin.composeClientApp.compose.calendar.CustomMonthHeader
import ru.zhogin.composeClientApp.compose.calendar.Schedule
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.PurpleGrey40
import ru.zhogin.composeClientApp.viewmodel.CalendarDayEventViewModel
import ru.zhogin.composeClientApp.viewmodel.CalendarDayViewModel
import java.time.LocalDate

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CalendarScreen(
    calendarDayViewModel: CalendarDayViewModel = hiltViewModel(),
    calendarDayEventViewModel: CalendarDayEventViewModel = hiltViewModel(),
    onNavigationCalendarDayAndEventsScreen: () -> Unit,
    onNavigationSuccessfulCalendarDayEvent: (calendarDayEvent: CalendarDayEvent) -> Unit,
) {


    val listOfCalendarDayEventsInDb =
        calendarDayEventViewModel.data.collectAsState(initial = emptyList())


    val date = remember {
        mutableStateOf(LocalDate.now().toString())
    }

    val displayListOfCalendarDayEvents =
        listOfCalendarDayEventsInDb.value.filter { calendarDayEvent ->
            calendarDayEvent.date == LocalDate.parse(date.value)
        }
    val calendarDayEventId = remember {
        mutableStateOf(0L)
    }

    val navController = rememberNavController()

    val weekends = remember {
        mutableStateOf("")
    }

    val openAlertDialog = remember {
        mutableStateOf(false)
    }
    when (openAlertDialog.value) {
        true -> {
            MyAlertDialog(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    calendarDayEventViewModel.removeDayEventById(calendarDayEventId.value)
                    openAlertDialog.value = false
                },
                dialogTitle = stringResource(id = R.string.delete),
                dialogText = stringResource(id = R.string.are_you_sure)
            )
        }

        else -> {}
    }
    Scaffold(
        floatingActionButtonPosition = androidx.compose.material.FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    weekends.value = date.value
                    onNavigationCalendarDayAndEventsScreen()
                },
                backgroundColor = Orange,

                ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        bottomBar = { BottomNavigationView(navController = navController) },
        backgroundColor = PurpleGrey40,

    ) {

        Card(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 60.dp, start = 4.dp, end = 4.dp),
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(1.dp, Color.Black),

        ) {


            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SelectableCalendar(
                    modifier = Modifier.size(320.dp),
                    dayContent = {
                        CustomDay(state = it,
                            onClick = { clickedDay ->
                                date.value = clickedDay.toString()
                                calendarDayViewModel.editedSelectedDay.value = clickedDay
                            })
                    },
                    monthHeader = { CustomMonthHeader(monthState = it) }
                )




                Schedule(
                    modifier = Modifier.padding(
                        start = 18.dp,

                        ),
                    calendarDayEvents = displayListOfCalendarDayEvents,
                    onClickDelete = {
                        calendarDayEventId.value = it.id
                        openAlertDialog.value = true
                    },
                    onClickDone = {
                        calendarDayEventViewModel.editedDayEvent.value = it
                        onNavigationSuccessfulCalendarDayEvent(it)
                    }
                )


            }


        }
    }
}








