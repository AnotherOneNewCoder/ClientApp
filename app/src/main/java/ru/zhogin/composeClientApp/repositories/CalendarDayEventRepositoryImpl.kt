package ru.zhogin.composeClientApp.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.zhogin.composeClientApp.dao.CalendarDayEventDao
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import ru.zhogin.composeClientApp.entity.CalendarDayEventEntity
import ru.zhogin.composeClientApp.entity.toCalendarDayEventList
import javax.inject.Inject

class CalendarDayEventRepositoryImpl @Inject constructor(
    private val calendarDayEventDao: CalendarDayEventDao
) : CalendarDayEventRepository {
    override val data: Flow<List<CalendarDayEvent>> = calendarDayEventDao.displayDayEvents().map {
        it.toCalendarDayEventList()
    }.flowOn(Dispatchers.Default)


    override suspend fun saveDayEvent(calendarDayEvent: CalendarDayEvent) {
        try {
            calendarDayEventDao.save(CalendarDayEventEntity.fromDto(calendarDayEvent))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun removeById(id: Long) {
        try {
            calendarDayEventDao.removeByID(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun removeAll() {
        try {
            calendarDayEventDao.removeAll()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun setDone(id: Long) {
        try {
            calendarDayEventDao.setDone(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}