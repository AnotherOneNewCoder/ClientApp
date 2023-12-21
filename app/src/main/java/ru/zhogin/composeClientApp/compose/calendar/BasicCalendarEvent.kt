package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import ru.zhogin.composeClientApp.dto.ColorType
import ru.zhogin.composeClientApp.ui.theme.MyBlue
import ru.zhogin.composeClientApp.ui.theme.MyGreyEvent
import ru.zhogin.composeClientApp.ui.theme.MyPink
import java.time.format.DateTimeFormatter

val calendarDayEventFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
fun BasicCalendarEvent(
    dayEvent: CalendarDayEvent,
    modifier: Modifier = Modifier,
    onClickDelete: (dayEvent: CalendarDayEvent) -> Unit,
    onClickDone: (dayEvent: CalendarDayEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(end = 2.dp)
            .background(
                color = if (dayEvent.done) MyGreyEvent
                else if (dayEvent.color == ColorType.GREEN) MyPink
                else MyBlue
//                when (dayEvent.color) {
//                    ColorType.DONE -> MyGreyEvent
//                    ColorType.GREEN -> MyPink
//                    else -> {
//                        MyBlue
//                    }
//                }
                , shape = RoundedCornerShape(4.dp)
            )

    ) {
        Row {
            Text(
                text = "${dayEvent.start.format(calendarDayEventFormatter)} - ${
                    dayEvent.end.format(
                        calendarDayEventFormatter
                    )
                }",
                fontSize = 14.sp,
            )


            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row {
                    if (!dayEvent.done) {
                        IconButton(
                            onClick = {
                                onClickDone(dayEvent)
                            },
                            modifier = Modifier
                                .size(20.dp)


                        ) {
                            Icon(
                                Icons.Filled.Done, contentDescription = "Done",
                                //tint = MyBlueDark
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            onClickDelete(dayEvent)
                        },
                        modifier = Modifier
                            .size(20.dp)

                    ) {
                        Icon(
                            Icons.Filled.Delete, contentDescription = "Delete", //tint = MyCherry
                        )
                    }


                }



            }


        }


        Text(
            text = dayEvent.name,
            //style = MaterialTheme.typography.body1,
            fontSize = 16.sp,
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




