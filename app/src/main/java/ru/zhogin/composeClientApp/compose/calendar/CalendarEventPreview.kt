package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import ru.zhogin.composeClientApp.dto.ColorType
import ru.zhogin.composeClientApp.ui.theme.ClientAppTheme
import java.time.LocalDate
import java.time.LocalDateTime

private val sampleCalendarEvents = listOf(
    CalendarDayEvent(
        name = "Google I/O Keynote",
        color = ColorType.WHITE,
        start = LocalDateTime.parse("2021-05-18T13:00:00"),
        end = LocalDateTime.parse("2021-05-18T15:00:00"),
        id = 3L,
        date = LocalDate.parse("2021-05-18"),
        description = "Tune in to find out about how we're furthering our mission to organize the world’s information and make it universally accessible and useful.",
    ),
    CalendarDayEvent(
        name = "Developer Keynote",
        color = ColorType.GREEN,
        start = LocalDateTime.parse("2021-05-18T15:15:00"),
        end = LocalDateTime.parse("2021-05-18T16:00:00"),
        id = 0L,
        date = LocalDate.parse("2021-05-18"),
        description = "Learn about the latest updates to our developer products and platforms from Google Developers.",
    ),
    CalendarDayEvent(
        name = "What's new in Android",
        color = ColorType.WHITE,
        start = LocalDateTime.parse("2021-05-18T16:50:00"),
        end = LocalDateTime.parse("2021-05-18T17:00:00"),
        id = 2L,
        date = LocalDate.parse("2021-05-18"),
        description = "In this Keynote, Chet Haase, Dan Sandler, and Romain Guy discuss the latest Android features and enhancements for developers.",
    ),
)

class CalendarEventsProvider: PreviewParameterProvider<CalendarDayEvent> {
    override val values: Sequence<CalendarDayEvent> = sampleCalendarEvents.asSequence()

}

@Preview(showBackground = true)
@Composable
fun CalendarEventPreview(
    @PreviewParameter(CalendarEventsProvider::class) calendarDayEvent: CalendarDayEvent
) {
    ClientAppTheme {
        BasicCalendarEvent(
            dayEvent = calendarDayEvent,
            modifier = Modifier.sizeIn(maxHeight = 64.dp))
    }
}