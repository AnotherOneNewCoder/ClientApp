package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import ru.zhogin.composeClientApp.ui.theme.ClientAppTheme
import java.time.LocalDate
import java.time.LocalDateTime

private val sampleCalendarEvents = listOf(
    CalendarDayEvent(
        name = "What's new in Android",
        color = Color(0xFF1B998B),
        start = LocalDateTime.parse("2021-05-18T01:50:00"),
        end = LocalDateTime.parse("2021-05-18T03:00:00"),
        id = 0L,
        date = LocalDate.parse("2021-05-18"),
        description = "In this Keynote, Chet Haase, Dan Sandler, and Romain Guy discuss the latest Android features and enhancements for developers.",
    ),
    CalendarDayEvent(
        name = "Google I/O Keynote",
        color = Color(0xFFAFBBF2),
        start = LocalDateTime.parse("2021-05-18T04:00:00"),
        end = LocalDateTime.parse("2021-05-18T05:00:00"),
        id = 1L,
        date = LocalDate.parse("2021-05-18"),
        description = "Tune in to find out about how we're furthering our mission to organize the world’s information and make it universally accessible and useful.",
    ),
    CalendarDayEvent(
        name = "Developer Keynote",
        color = Color(0xFFAFBBF2),
        start = LocalDateTime.parse("2021-05-18T06:15:00"),
        end = LocalDateTime.parse("2021-05-18T10:00:00"),
        id = 2L,
        date = LocalDate.parse("2021-05-18"),
        description = "Learn about the latest updates to our developer products and platforms from Google Developers.",
    ),

)
@Preview(showBackground = true)
@Composable
fun SchedulePreview() {
    ClientAppTheme {
        Schedule(sampleCalendarEvents)
    }
}