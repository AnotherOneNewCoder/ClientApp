package ru.zhogin.composeClientApp.dto

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

data class CalendarDayEvent(
    val isWeekend: Boolean = false,
    val isWorkingDay: Boolean = false,
    val name: String,
    val color: Color,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val description: String? = null
)
