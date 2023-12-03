package ru.zhogin.composeClientApp.compose.screens

import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mabn.calendarlibrary.ExpandableCalendar
import com.mabn.calendarlibrary.core.calendarDefaultTheme
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.compose.calendar.CustomDay
import ru.zhogin.composeClientApp.compose.calendar.CustomMonthHeader
import ru.zhogin.composeClientApp.compose.calendar.Schedule
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import ru.zhogin.composeClientApp.dto.ColorType
import ru.zhogin.composeClientApp.ui.theme.MyWhite
import ru.zhogin.composeClientApp.ui.theme.Orange
import ru.zhogin.composeClientApp.ui.theme.Purple40
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CalendarScreen() {
    val date = remember {
        mutableStateOf("")
    }
//    val date = remember {
//        mutableStateOf<LocalDate>(LocalDate.now())
//    }

    val weekends = remember {
        mutableStateOf("")
    }
    val sampleCalendarEvents = listOf(

        CalendarDayEvent(
            name = "Клиент 134",
            color = ColorType.WHITE,
            start = LocalDateTime.parse("2021-05-18T10:00:00"),
            end = LocalDateTime.parse("2021-05-18T11:00:00"),
            id = 0L,
            date = LocalDate.parse("2021-05-18"),
            description = "Tune in to find out about how we're furthering our mission to organize the world’s information and make it universally accessible and useful.",
        ),
        CalendarDayEvent(
            name = "Клиент 186",
            color = ColorType.GREEN,
            start = LocalDateTime.parse("2021-05-18T15:15:00"),
            end = LocalDateTime.parse("2021-05-18T16:00:00"),
            id = 1L,
            date = LocalDate.parse("2021-05-18"),
            description = "Learn about the latest updates to our developer products and platforms from Google Developers.",
        ),
        CalendarDayEvent(
            name = "Клиент 234",
            color = ColorType.WHITE,
            start = LocalDateTime.parse("2021-05-18T01:50:00"),
            end = LocalDateTime.parse("2021-05-18T03:20:00"),
            id = 2L,
            date = LocalDate.parse("2021-05-18"),
            description = "In this Keynote, Chet Haase, Dan Sandler, and Romain Guy discuss the latest Android features and enhancements for developers.",
        ),
        )

    Surface(
        modifier = Modifier.fillMaxSize(),
        content = {
            Box(modifier = with(Modifier) {
                fillMaxSize()
                    .background(Color.Black)
                    .paint(
                        painterResource(id = R.drawable.cat_profile),
                        contentScale = ContentScale.Crop
                    )



            }, contentAlignment = Alignment.TopCenter)

            {
                Card(
                    modifier = Modifier.padding(top = 4.dp),
                    shape = RoundedCornerShape(32.dp),
                    border = BorderStroke(1.dp, Purple40)
                ) {


                    Column(
                        modifier = Modifier


                            ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                        AndroidView(factory = { CalendarView(it) }, update = {
//
//
//                            it.setOnDateChangeListener { calendarView, year, month, day ->
//
//                                date.value = "$day - ${month + 1} - $year"
//
//                            }
//                            it.firstDayOfWeek = Calendar.MONDAY
//
//                        })
//                        ExpandableCalendar(theme = calendarDefaultTheme.copy(
//                            dayBackgroundColor = if (date.value == weekends.value) {
//                                Color.Red
//                            } else {
//                                Color.Transparent
//                            }
//                        ),onDayClick = {
//                            date.value = it.toString()
//                        }

//                        )
                        SelectableCalendar(
                            modifier = Modifier.size(320.dp),
                            dayContent = { CustomDay(state = it,
                                onClick = { clickedDay ->
                                    date.value = clickedDay.toString()

                                })},
                            monthHeader = { CustomMonthHeader(monthState = it)}
                        )


//                        Text(text = date.value.toString())
//                        Text(text = weekends.value.toString())

                        Schedule(
                            modifier = Modifier.padding(start = 24.dp, bottom = 44.dp)
  //                              .fillMaxWidth(0.9f)
//                                .verticalScroll(
//                                    rememberScrollState())

                            ,
                            calendarDayEvents = sampleCalendarEvents)


                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 64.dp, end = 16.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        FloatingActionButton(
                            onClick = {
                                weekends.value = date.value
                            },
                            backgroundColor = Orange,

                            ) {
                            Icon(Icons.Filled.Add, contentDescription = "Add")
                        }
                    }

                }
            }
        }


    )


}