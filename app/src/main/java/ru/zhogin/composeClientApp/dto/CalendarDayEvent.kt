package ru.zhogin.composeClientApp.dto

import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.time.LocalDateTime

data class CalendarDayEvent(
    val id: Long,
    val date: LocalDate,
    val name: String,
    val color: ColorType,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val done: Boolean = false,
    val clientId: Long,
    val description: String? = null
)
enum class ColorType{
    GREEN, WHITE
}

