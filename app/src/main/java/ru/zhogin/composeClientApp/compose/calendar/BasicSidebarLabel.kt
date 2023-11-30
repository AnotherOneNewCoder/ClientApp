package ru.zhogin.composeClientApp.compose.calendar

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val hourFormatter = DateTimeFormatter.ofPattern("h a")
@Composable
fun BasicSidebarLabel(
    time: LocalTime,
    modifier: Modifier = Modifier,
) {
    Text(
        text = time.format(hourFormatter),
        modifier = modifier
            .fillMaxHeight()
            .padding(4.dp)
    )
}