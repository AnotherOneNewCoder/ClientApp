package ru.zhogin.composeClientApp.compose.screens

import android.icu.util.Calendar
import android.widget.CalendarView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import ru.zhogin.composeClientApp.R
import ru.zhogin.composeClientApp.ui.theme.MyWhite
import ru.zhogin.composeClientApp.ui.theme.Purple40

@Composable
fun CalendarScreen() {
    val date = remember {
        mutableStateOf("")
    }
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
                            .fillMaxWidth(0.9f).background(MyWhite)
                            ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AndroidView(factory = { CalendarView(it) }, update = {
                            it.setOnDateChangeListener { calendarView, year, month, day ->

                                date.value = "$day - ${month + 1} - $year"
                            }
                            it.firstDayOfWeek = Calendar.MONDAY
                        })
                        Text(text = date.value)
                    }
                }
            }
        }
    )


}