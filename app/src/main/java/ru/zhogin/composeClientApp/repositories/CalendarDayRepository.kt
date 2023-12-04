package ru.zhogin.composeClientApp.repositories

import kotlinx.coroutines.flow.Flow
import ru.zhogin.composeClientApp.dto.CalendarDay

interface CalendarDayRepository {
    val data: Flow<List<CalendarDay>>

    suspend fun removeById(id: Long)
    suspend fun removeAll()

    suspend fun save(calendarDay: CalendarDay)

    suspend fun isWeekend(id: Long)
    suspend fun isWorkingDay(id: Long)
}