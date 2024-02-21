package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.header.MonthState
import ru.zhogin.composeClientApp.ui.theme.Brize2
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CustomMonthHeader(
    monthState: MonthState,
    modifier: Modifier = Modifier,
) {

        Row(
            modifier = modifier.fillMaxWidth().background(Brize2),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                modifier = Modifier.testTag("Decrement"),
                onClick = { monthState.currentMonth = monthState.currentMonth.minusMonths(1) }
            ) {
                Image(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                    contentDescription = "Previous",
                )
            }
            Text(
                modifier = Modifier.testTag("MonthLabel"),
                text = monthState.currentMonth.month
                    .getDisplayName(TextStyle.FULL, Locale.getDefault())
                    .lowercase()
                    .replaceFirstChar { it.titlecase() },
                style = MaterialTheme.typography.subtitle1,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = monthState.currentMonth.year.toString(), style = MaterialTheme.typography.subtitle1)
            IconButton(
                modifier = Modifier.testTag("Increment"),
                onClick = { monthState.currentMonth = monthState.currentMonth.plusMonths(1) }
            ) {
                Image(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                    contentDescription = "Next",
                )
            }
        }


}