package ru.zhogin.composeClientApp.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.zhogin.composeClientApp.dao.CalendarDayDao
import ru.zhogin.composeClientApp.dto.CalendarDay
import ru.zhogin.composeClientApp.entity.CalendarDayEntity
import ru.zhogin.composeClientApp.entity.toCalendarDayList
import javax.inject.Inject

class CalendarDayRepositoryImpl @Inject constructor(
    private val calendarDayDao: CalendarDayDao
) : CalendarDayRepository {
    override val data: Flow<List<CalendarDay>> = calendarDayDao.getAll().map {
        it.toCalendarDayList()
    }.flowOn(Dispatchers.Default)


    override suspend fun removeById(id: Long) {
        try {
            calendarDayDao.removeByID(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun removeAll() {
        try {
            calendarDayDao.removeAll()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun save(calendarDay: CalendarDay) {
        try {
            calendarDayDao.save(CalendarDayEntity.fromDto(calendarDay))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun isWeekend(id: Long) {
        try {
            calendarDayDao.setWeekend(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun isWorkingDay(id: Long) {
        try {
            calendarDayDao.setWorkingDay(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}