package ru.zhogin.composeClientApp.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.zhogin.composeClientApp.dao.CalendarDayDao
import ru.zhogin.composeClientApp.dto.CalendarDay
import ru.zhogin.composeClientApp.entity.CalendarDayEntity
import ru.zhogin.composeClientApp.entity.toCalendarDayList
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
private val dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
class CalendarDayRepositoryImpl @Inject constructor(
    private val calendarDayDao: CalendarDayDao
) : CalendarDayRepository {
    override val data: Flow<List<CalendarDay>> = calendarDayDao.getAll().map {
        it.forEach {calendarDayEntity ->
            if ((LocalDate.parse(calendarDayEntity.date, dateFormatter).plusYears(2)) < LocalDate.now()) {
                calendarDayDao.removeByID(calendarDayEntity.id)
            }
        }
        it.toCalendarDayList()
    }.flowOn(Dispatchers.Default)



//    listOfCalendarDaysInDb.value.forEach { calendarDay ->
//        if ((LocalDate.parse(calendarDay.date, dateFormatter).plusYears(2)) < LocalDate.now() )
//            calendarDayViewModel.removeDayById(calendarDay.id)
//    }

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