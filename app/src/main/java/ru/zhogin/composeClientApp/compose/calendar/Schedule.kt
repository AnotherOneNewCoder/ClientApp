package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

@Composable
fun Schedule(
    calendarDayEvents: List<CalendarDayEvent>,
    modifier: Modifier = Modifier,
    calendarDayEventContent: @Composable (calendarDayEvent: CalendarDayEvent) -> Unit = { BasicCalendarEvent(
        dayEvent = it
    )},
) {
    val hourHeight = 64.dp

    Layout(
        content = {
            calendarDayEvents.sortedBy(CalendarDayEvent::start).forEach { calendarDayEvent ->
                //calendarDayEventContent(calendarDayEvent)
                Box(modifier = Modifier.CalendarDayEventData(calendarDayEvent)) {
                    calendarDayEventContent(calendarDayEvent)
                }
            }
        },
        modifier = modifier
            .verticalScroll(rememberScrollState()),
    )
    {measureables, constraints ->
        val height = hourHeight.roundToPx() * 24
        val placeables = measureables.map { measurable ->
            val calendarDayEvent = measurable.parentData as CalendarDayEvent
            val calendarDayEventDurationMinutes = ChronoUnit.MINUTES.between(calendarDayEvent.start, calendarDayEvent.end)
            val calendarDayEventHeight = ((calendarDayEventDurationMinutes / 60f) * hourHeight.toPx()).roundToInt()
            val placeable = measurable.measure(constraints.copy(minHeight = calendarDayEventHeight,maxHeight = calendarDayEventHeight))
            Pair(placeable,calendarDayEvent)
        }
        layout(constraints.maxWidth, height) {
            placeables.forEach { (placeable, calendarDayEvent) ->
                val calendarDayEventOffSetMinutes = ChronoUnit.MINUTES.between(LocalTime.MIN, calendarDayEvent.start.toLocalTime())
                val calendarDayEventY = ((calendarDayEventOffSetMinutes / 60f) * hourHeight.toPx()).roundToInt()
                placeable.place(0, calendarDayEventY)
            }
        }
    }

}

private class CalendarDayEventDataModifier(
    val calendarDayEvent: CalendarDayEvent,
): ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = calendarDayEvent
}
private fun Modifier.CalendarDayEventData(calendarDayEvent: CalendarDayEvent) = this.then(CalendarDayEventDataModifier(calendarDayEvent))