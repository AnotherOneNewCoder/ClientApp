package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

@Composable
fun BasicSchedule(
    calendarDayEvents: List<CalendarDayEvent>,
    modifier: Modifier = Modifier,
    minDate: LocalDate? = calendarDayEvents.minByOrNull(CalendarDayEvent::start)?.start?.toLocalDate(),
    maxDate: LocalDate? = calendarDayEvents.minByOrNull(CalendarDayEvent::end)?.end?.toLocalDate(),
    onClickDelete: (calendarDayEvent: CalendarDayEvent) -> Unit,
    onClickDone: (calendarDayEvent: CalendarDayEvent) -> Unit,
    calendarDayEventContent: @Composable (calendarDayEvent: CalendarDayEvent) -> Unit = { BasicCalendarEvent(
        dayEvent = it,
        onClickDelete = { onClickDelete(it) },
        onClickDone = { onClickDone(it) }
    )},
    hourHeight: Dp,
    dayWidth: Dp,
) {
    val dividerColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray

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
            .drawBehind {
                repeat(23) {
                    drawLine(
                        dividerColor,
                        start = Offset(0f, (it + 1) * hourHeight.toPx()),
                        end = Offset(size.width, (it + 1) * hourHeight.toPx()),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }
//            .verticalScroll(rememberScrollState()),
    )
    {measureables, constraints ->
        val height = hourHeight.roundToPx() * 24
        val width = dayWidth.roundToPx()
        val placeables = measureables.map { measurable ->
            val calendarDayEvent = measurable.parentData as CalendarDayEvent
            val calendarDayEventDurationMinutes = ChronoUnit.MINUTES.between(calendarDayEvent.start, calendarDayEvent.end)
            val calendarDayEventHeight = ((calendarDayEventDurationMinutes / 60f) * hourHeight.toPx()).roundToInt()
            val placeable = measurable.measure(constraints.copy(minWidth = dayWidth.roundToPx(), maxWidth = dayWidth.roundToPx(),minHeight = calendarDayEventHeight,maxHeight = calendarDayEventHeight))
            Pair(placeable,calendarDayEvent)
        }
        layout(width, height) {
            placeables.forEach { (placeable, calendarDayEvent) ->
                val calendarDayEventOffSetMinutes = ChronoUnit.MINUTES.between(LocalTime.MIN, calendarDayEvent.start.toLocalTime())
                val calendarDayEventY = ((calendarDayEventOffSetMinutes / 60f) * hourHeight.toPx()).roundToInt()
                val calendarDayEventOffDays = ChronoUnit.DAYS.between(minDate, calendarDayEvent.start.toLocalDate()).toInt()
                val calendarDayEventX = calendarDayEventOffDays * dayWidth.roundToPx()
                placeable.place(calendarDayEventX, calendarDayEventY)
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