package ru.zhogin.composeClientApp.entity

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import java.time.LocalDate
import java.time.LocalDateTime
@Entity
data class CalendarDayEventEntity(
    @PrimaryKey
    val id: Long,
    val date: LocalDate,
    val name: String,
    val color: Color,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val description: String? = null
) {
    fun toDto() = CalendarDayEvent(
        id = id,
        date = date,
        name = name,
        color = color,
        start = start,
        end = end,
        description = description
    )

    companion object{
        fun fromDto(calendarDayEvent: CalendarDayEvent): CalendarDayEventEntity = CalendarDayEventEntity(
            id = calendarDayEvent.id,
            date = calendarDayEvent.date,
            name = calendarDayEvent.name,
            color = calendarDayEvent.color,
            start = calendarDayEvent.start,
            end = calendarDayEvent.end,
            description = calendarDayEvent.description
        )
    }
}
fun List<CalendarDayEventEntity>.toCalendarDayEventList() = map(CalendarDayEventEntity::toDto)
fun List<CalendarDayEvent>.toCalendarDayEventEntityList() = map(CalendarDayEventEntity::fromDto)
