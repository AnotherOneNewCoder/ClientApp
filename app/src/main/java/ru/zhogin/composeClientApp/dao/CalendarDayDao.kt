package ru.zhogin.composeClientApp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zhogin.composeClientApp.entity.CalendarDayEntity

import java.time.LocalDate

@Dao
interface CalendarDayDao {
    @Query("SELECT * FROM CalendarDayEntity ORDER BY id DESC")
    fun getAll(): Flow<List<CalendarDayEntity>>

    suspend fun save(calendarDay: CalendarDayEntity) =
        if (calendarDay.id == 0L) insert(calendarDay) else updateCalendarDayByID(calendarDay.id, calendarDay.date, calendarDay.isWeekend, calendarDay.isWorkingDay)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calendarDay: CalendarDayEntity)

    @Query(
        """
           UPDATE CalendarDayEntity SET
               date = date,
               isWeekend = isWeekend,
               isWorkingDay = isWorkingDay
           WHERE id = :id
        """
    )
    suspend fun updateCalendarDayByID(id: Long, date: LocalDate, isWeekend: Boolean, isWorkingDay: Boolean)


    @Query("DELETE FROM CalendarDayEntity WHERE id = :id")
    suspend fun removeByID(id: Long)
    @Query("DELETE FROM CalendarDayEntity")
    suspend fun removeAll()

    @Query("SELECT * from CalendarDayEntity WHERE id = :id")
    suspend fun getByID(id: Long) : CalendarDayEntity




}