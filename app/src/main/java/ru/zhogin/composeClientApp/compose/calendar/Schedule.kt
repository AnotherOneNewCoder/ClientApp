package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import java.time.LocalDate

@Composable
fun Schedule(
    calendarDayEvents: List<CalendarDayEvent>,
    modifier: Modifier = Modifier,
    minDate: LocalDate? = calendarDayEvents.minByOrNull(CalendarDayEvent::start)?.start?.toLocalDate(),
    maxDate: LocalDate? = calendarDayEvents.minByOrNull(CalendarDayEvent::end)?.end?.toLocalDate(),
    calendarDayEventContent: @Composable (calendarDayEvent: CalendarDayEvent) -> Unit = { BasicCalendarEvent(
        dayEvent = it
    )},
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it)}
) {
    val hourHeight = 64.dp
    val verticalScrollState = rememberScrollState()
    val verticalScrollState2 = rememberScrollState()
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
            )
            BasicSchedule(
                calendarDayEvents = calendarDayEvents,
                hourHeight = hourHeight,
                modifier = modifier

                    .verticalScroll(verticalScrollState),
                minDate = minDate,
                maxDate = maxDate,
                calendarDayEventContent = calendarDayEventContent,
            )


        }





    }

}