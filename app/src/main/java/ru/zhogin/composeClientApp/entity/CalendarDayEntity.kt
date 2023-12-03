package ru.zhogin.composeClientApp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zhogin.composeClientApp.dto.CalendarDay
import java.time.LocalDate


@Entity
data class CalendarDayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: LocalDate,
    val isWeekend: Boolean = false,
    val isWorkingDay: Boolean = false,
) {
    fun toDto() = CalendarDay(
        id = id,
        date = date,
        isWeekend = isWeekend,
        isWorkingDay = isWorkingDay,
    )

    companion object {
        fun fromDto(calendarDay: CalendarDay): CalendarDayEntity =
            CalendarDayEntity(
                id = calendarDay.id,
                date = calendarDay.date,
                isWorkingDay = calendarDay.isWorkingDay,
                isWeekend = calendarDay.isWeekend,
            )
    }
}
fun List<CalendarDayEntity>.toCalendarDayList() = map(CalendarDayEntity::toDto)
fun List<CalendarDay>.toCalendarDayEntityList() = map(CalendarDayEntity::fromDto)