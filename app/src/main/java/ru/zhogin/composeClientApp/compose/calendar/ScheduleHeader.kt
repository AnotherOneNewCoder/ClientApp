package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.ui.theme.Brize2
import java.time.LocalDate

@Composable
fun ScheduleHeader(
    minDate: LocalDate?,
    maxDate: LocalDate?,
    modifier: Modifier = Modifier,
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it)},
) {
    Row(modifier = modifier.background(Brize2)) {
        Box {
            if (minDate != null) {
                dayHeader(minDate)
            } else {
                Text(
                    text = stringResource(id = R.string.no_record),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp)

                )
            }

        }
    }
}