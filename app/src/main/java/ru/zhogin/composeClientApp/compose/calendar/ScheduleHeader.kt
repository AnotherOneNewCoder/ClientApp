package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.LocalDate

@Composable
fun ScheduleHeader(
    minDate: LocalDate?,
    maxDate: LocalDate?,
    modifier: Modifier = Modifier,
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it)},
) {
    Row(modifier = modifier) {
        Box {
            if (minDate != null) {
                dayHeader(minDate)
            }

        }
    }
}