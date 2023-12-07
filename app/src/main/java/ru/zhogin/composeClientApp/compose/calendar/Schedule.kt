package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import ru.zhogin.composeClientApp.compose.alertdialog.MyAlertDialog
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import java.time.LocalDate

@Composable
fun Schedule(
    calendarDayEvents: List<CalendarDayEvent>,
    modifier: Modifier = Modifier,
    minDate: LocalDate? = calendarDayEvents.minByOrNull(CalendarDayEvent::start)?.start?.toLocalDate(),
    //minDate: LocalDate? = calendarDayEvents.minByOrNull(CalendarDayEvent::start)?.LocalDate.parse(start?).toLocalDate(),
    maxDate: LocalDate? = calendarDayEvents.minByOrNull(CalendarDayEvent::end)?.end?.toLocalDate(),
    onClickDelete: (calendarDayEvent: CalendarDayEvent) -> Unit,
    onClickDone: (calendarDayEvent: CalendarDayEvent) -> Unit,
    calendarDayEventContent: @Composable (calendarDayEvent: CalendarDayEvent) -> Unit = { BasicCalendarEvent(
        dayEvent = it,
        onClickDelete = { onClickDelete(it) },
        onClickDone = { onClickDone(it) }
    )},
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it)}
) {
    val hourHeight = 84.dp
    val dayWidth = 256.dp
    val verticalScrollState = rememberScrollState()

    var sideBarWidth by remember {
        mutableStateOf(0)
    }



    Column(modifier = modifier) {
        ScheduleHeader(
            minDate = minDate,
            maxDate = maxDate,
            dayHeader = dayHeader,

            )

        Row(

        ){
            ScheduleSideBar(
                hourHeight = hourHeight,
                modifier = modifier.verticalScroll(verticalScrollState)
                    .onGloballyPositioned { sideBarWidth = it.size.width }
            )
            BasicSchedule(
                calendarDayEvents = calendarDayEvents,
                hourHeight = hourHeight,
                modifier = modifier

                    .verticalScroll(verticalScrollState)
                    ,
                minDate = minDate,
                maxDate = maxDate,
                calendarDayEventContent = calendarDayEventContent,
                dayWidth = dayWidth,
                onClickDelete = {onClickDelete(it)},
                onClickDone = { onClickDone(it) }
            )



        }





    }

}