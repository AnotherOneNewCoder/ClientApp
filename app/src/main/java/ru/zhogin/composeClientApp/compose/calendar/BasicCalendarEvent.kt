package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.zhogin.composeClientApp.dto.ColorType
import ru.zhogin.composeClientApp.ui.theme.MenColor
import ru.zhogin.composeClientApp.ui.theme.WomenColor
import java.time.format.DateTimeFormatter

val calendarDayEventFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
fun BasicCalendarEvent(
    dayEvent: CalendarDayEvent,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(end = 2.dp, bottom = 2.dp)
            .background(
                color = if (dayEvent.color == ColorType.GREEN) {
                    WomenColor
                } else {
                    MenColor
                }, shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp)
    ) {
        Text(
            text = "${dayEvent.start.format(calendarDayEventFormatter)} - ${
                dayEvent.end.format(
                    calendarDayEventFormatter
                )
            }",
            style = MaterialTheme.typography.caption
        )

        Text(
            text = dayEvent.name,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
        )
        if (dayEvent.description != null) {
            Text(
                text = dayEvent.description,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }


}