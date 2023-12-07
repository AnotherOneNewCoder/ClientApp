package ru.zhogin.composeClientApp.repositories

import kotlinx.coroutines.flow.Flow
import ru.zhogin.composeClientApp.dto.CalendarDayEvent

interface CalendarDayEventRepository {
    val data: Flow<List<CalendarDayEvent>>
    suspend fun saveDayEvent(calendarDayEvent: CalendarDayEvent)
    suspend fun removeById(id: Long)
    suspend fun removeAll()
    suspend fun setDone(id: Long)

}