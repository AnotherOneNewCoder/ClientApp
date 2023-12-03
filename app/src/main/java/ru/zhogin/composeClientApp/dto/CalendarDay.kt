package ru.zhogin.composeClientApp.dto



data class CalendarDay(
    val id: Long,
    val date: String,
    val isWeekend: Boolean = false,
    val isWorkingDay: Boolean = false,

)
