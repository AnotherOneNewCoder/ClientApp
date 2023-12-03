package ru.zhogin.composeClientApp.dto

import java.time.LocalDate

data class CalendarDay(
    val id: Long,
    val date: LocalDate,
    val isWeekend: Boolean = false,
    val isWorkingDay: Boolean = false,

)
