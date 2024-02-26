package ru.zhogin.composeClientApp.compose.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.alertdialog.DeleteDialogBox
import ru.zhogin.composeClientApp.compose.calendar.CustomDay
import ru.zhogin.composeClientApp.compose.calendar.CustomDaysOfWeekHeader
import ru.zhogin.composeClientApp.compose.calendar.CustomMonthHeader
import ru.zhogin.composeClientApp.compose.calendar.Schedule
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import ru.zhogin.composeClientApp.ui.theme.Brize2
import ru.zhogin.composeClientApp.ui.theme.MyGrey2
import ru.zhogin.composeClientApp.viewmodel.CalendarDayEventViewModel
import ru.zhogin.composeClientApp.viewmodel.CalendarDayViewModel
import java.time.LocalDate


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


    val weekends = remember {
        mutableStateOf("")
    }

    val openAlertDialog = remember {
        mutableStateOf(false)
    }
    when (openAlertDialog.value) {
        true -> {
            DeleteDialogBox(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = { calendarDayEventViewModel.removeDayEventById(calendarDayEventId.value)
                    openAlertDialog.value = false },
                dialogTitle = stringResource(id = R.string.delete),
                dialogText = stringResource(id = R.string.are_you_sure),
                modifier = Modifier,
            )
        }

        else -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MyGrey2),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 4.dp, bottom = 4.dp, start = 4.dp, end = 4.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = 16.dp
            //border = BorderStroke(1.dp, Color.Black),

            ) {


            SelectableCalendar(
                //modifier = Modifier.size(320.dp),
                dayContent = {
                    CustomDay(state = it,
                        onClick = { clickedDay ->
                            date.value = clickedDay.toString()
                            calendarDayViewModel.editedSelectedDay.value = clickedDay
                        })
                },
                monthHeader = { CustomMonthHeader(monthState = it) },
                daysOfWeekHeader = { CustomDaysOfWeekHeader(daysOfWeek = it) }
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 4.dp, bottom = 4.dp, start = 4.dp, end = 4.dp),
            shape = RoundedCornerShape(16.dp),
            //border = BorderStroke(1.dp, Color.Black),
            elevation = 16.dp

            ) {

            Schedule(
                modifier = Modifier.fillMaxHeight(),
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 8.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                weekends.value = date.value
                onNavigationCalendarDayAndEventsScreen()
            },
            backgroundColor = Brize2,

            ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
}









