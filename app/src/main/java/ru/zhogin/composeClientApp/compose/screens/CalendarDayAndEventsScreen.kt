package ru.zhogin.composeClientApp.compose.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.viewmodel.CalendarDayViewModel


@Composable
fun CalendarDayAndEventsScreen(
    calendarDayViewModel: CalendarDayViewModel = hiltViewModel(),
    onNavigationBack: () -> Unit,
) {
    val calendarDayDate = remember {
        mutableStateOf(calendarDayViewModel.editedSelectedDay.value)
    }
    val checkedStateWeekend = remember { mutableStateOf(false) }
    val checkedStateWorkingDay = remember { mutableStateOf(false) }


    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .padding(4.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = Color.Black,
                fontSize = 22.sp,
                text = calendarDayDate.value.toString(),
                )
            Row {
                Checkbox(
                    checked = checkedStateWorkingDay.value,
                    onCheckedChange = { checkedStateWorkingDay.value = it },
                    modifier = Modifier.padding(top = 5.dp, end = 5.dp)
                )
                Text("Working day", fontSize = 22.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 12.dp))
            }

            Row {
                Checkbox(
                    checked = checkedStateWeekend.value,
                    onCheckedChange = { checkedStateWeekend.value = it },
                    modifier = Modifier.padding(top = 5.dp, end = 5.dp)
                )
                Text("Weekend", fontSize = 22.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 12.dp))
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp, end = 16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingActionButton(
                    onClick = {
                        calendarDayViewModel.editedDay.value =
                            calendarDayViewModel.editedDay.value?.copy(
                                isWeekend = checkedStateWeekend.value,
                                isWorkingDay = checkedStateWorkingDay.value,
                                date = calendarDayDate.value.toString()
                            )
                        calendarDayViewModel.saveDay()
                        calendarDayViewModel.clearData()
                        onNavigationBack()
                    },
                    backgroundColor = Orange,

                    ) {
                    Icon(Icons.Filled.Done, contentDescription = "Done")
                }
            }
        }

    }
}