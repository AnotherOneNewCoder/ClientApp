package ru.zhogin.composeClientApp.entity

import androidx.compose.ui.graphics.Color
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import ru.zhogin.composeClientApp.dto.ColorType
import java.time.LocalDate
import java.time.LocalDateTime
@Entity
data class CalendarDayEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: String,
    val name: String,
    @Embedded
    val color: ColorTypeEmbedded,
    val start: String,
    val end: String,
    val description: String? = null
) {
    fun toDto() = CalendarDayEvent(
        id = id,
        date = LocalDate.parse(date),
        name = name,
        color = color.toDto(),
        start = LocalDateTime.parse(start),
        end = LocalDateTime.parse(end),
        description = description
    )

    companion object{
        fun fromDto(calendarDayEvent: CalendarDayEvent): CalendarDayEventEntity = CalendarDayEventEntity(
            id = calendarDayEvent.id,
            date = calendarDayEvent.date.toString(),
            name = calendarDayEvent.name,
            color = ColorTypeEmbedded.fromDto(calendarDayEvent.color),
            start = calendarDayEvent.start.toString(),
            end = calendarDayEvent.end.toString(),
            description = calendarDayEvent.description
        )
    }
}
fun List<CalendarDayEventEntity>.toCalendarDayEventList() = map(CalendarDayEventEntity::toDto)
fun List<CalendarDayEvent>.toCalendarDayEventEntityList() = map(CalendarDayEventEntity::fromDto)
