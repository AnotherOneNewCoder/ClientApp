package ru.zhogin.composeClientApp.dao

import androidx.compose.ui.graphics.Color
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


import ru.zhogin.composeClientApp.entity.CalendarDayEventEntity
import java.time.LocalDate
import java.time.LocalDateTime


@Dao
interface CalendarDayEventDao {
    @Query("SELECT * FROM CalendarDayEventEntity WHERE date LIKE :date")
    fun displayDayEvents(date: LocalDate): Flow<List<CalendarDayEventEntity>>




    suspend fun save(calendarDayEvent: CalendarDayEventEntity) =
        if (calendarDayEvent.id == 0L) insert(calendarDayEvent) else updateCalendarDayEventByID(calendarDayEvent.id, calendarDayEvent.date,
            calendarDayEvent.name, calendarDayEvent.color, calendarDayEvent.start, calendarDayEvent.end, calendarDayEvent.description)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calendarDayEvent: CalendarDayEventEntity)

    @Query(
        """
           UPDATE CalendarDayEventEntity SET
               date = date,
               name = name,
               color = color,
               start = start,
               end = end,
               description = description
           WHERE id = :id
        """
    )
    suspend fun updateCalendarDayEventByID(id: Long, date: LocalDate, name: String, color: Color, start: LocalDateTime, end: LocalDateTime, description: String?)

    @Query("DELETE FROM CalendarDayEventEntity WHERE id = :id")
    suspend fun removeByID(id: Long)
    @Query("DELETE FROM CalendarDayEventEntity")
    suspend fun removeAll()

    @Query("SELECT * from CalendarDayEventEntity WHERE id = :id")
    suspend fun getByID(id: Long) : CalendarDayEventEntity

}