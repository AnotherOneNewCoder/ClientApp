package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import ru.zhogin.composeClientApp.ui.theme.Brize2
import ru.zhogin.composeClientApp.ui.theme.MyBlue
import ru.zhogin.composeClientApp.ui.theme.MyRed
import ru.zhogin.composeClientApp.viewmodel.CalendarDayViewModel
import java.time.LocalDate

/**
 * Default implementation for day content. It supports different appearance for days from
 * current and adjacent month, as well as current day and selected day
 *
 * @param selectionColor color of the border, when day is selected
 * @param currentDayColor color of content for the current date
 * @param onClick callback for interacting with day clicks
 */
@Composable
fun <T : SelectionState> CustomDay(
    state: DayState<T>,
    modifier: Modifier = Modifier,
    selectionColor: Color = MyBlue,
    currentDayColor: Color = MaterialTheme.colors.primary,
    onClick: (LocalDate) -> Unit = {},
    calendarDayViewModel: CalendarDayViewModel = hiltViewModel()
) {
    val date = state.date
    val selectionState = state.selectionState
    val listOfCalendarDaysInDb = calendarDayViewModel.data.collectAsState(initial = emptyList())
    val isSelected = selectionState.isDateSelected(date)


    val selectedList = remember {
        mutableListOf<LocalDate>()
    }
    val listOfWorkingDays = listOfCalendarDaysInDb.value.filter { calendarDay ->
        calendarDay.isWorkingDay
    }
    val listOfWeekends = listOfCalendarDaysInDb.value.filter { calendarDay ->
        calendarDay.isWeekend
    }
    val isSelectedWorkingDay = listOfWorkingDays.map {
        it.date
    }
    val isSelectedWeekend = listOfWeekends.map {
        it.date
    }

//    val isSelected2 = selectedList.contains(date)
//    val isSelected3 = listOfCalendarDaysInDb.value.map {calendarDay ->
//        calendarDay.date
//    }

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp),
        elevation = if (state.isFromCurrentMonth) 4.dp else 0.dp,
        border = if (state.isCurrentDay) BorderStroke(1.dp, currentDayColor) else null,
        contentColor = if (isSelectedWorkingDay.contains(date.toString())) Brize2
        else if (isSelectedWeekend.contains(date.toString())) MyRed
        else if (isSelected) selectionColor
        else contentColorFor(
            backgroundColor = MaterialTheme.colors.surface
        )
    ) {
        Box(
            modifier = Modifier.clickable {
                onClick(date)
                selectionState.onDateSelected(date)
                selectedList.add(date)
            },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = date.dayOfMonth.toString())
        }
    }
}
