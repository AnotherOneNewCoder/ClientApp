package ru.zhogin.composeClientApp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.zhogin.composeClientApp.entity.CalendarDayEventEntity



@Dao
interface CalendarDayEventDao {
    @Query("SELECT * FROM CalendarDayEventEntity ORDER BY id DESC")
    fun displayDayEvents(): Flow<List<CalendarDayEventEntity>>




    suspend fun save(calendarDayEvent: CalendarDayEventEntity) =
        if (calendarDayEvent.id == 0L) insert(calendarDayEvent) else updateCalendarDayEventByID(calendarDayEvent.id, calendarDayEvent.date,
            calendarDayEvent.name,  calendarDayEvent.start, calendarDayEvent.end, calendarDayEvent.clientId ,calendarDayEvent.description)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calendarDayEvent: CalendarDayEventEntity)

    @Query(
        """
           UPDATE CalendarDayEventEntity SET
               date = :date,
               name = :name,
               start = :start,
               end = :end,
               clientId = :clientId,
               description = :description
           WHERE id = :id
        """
    )
    suspend fun updateCalendarDayEventByID(id: Long, date: String, name: String, start: String, end: String, clientId: Long,description: String?)

    @Query("DELETE FROM CalendarDayEventEntity WHERE id = :id")
    suspend fun removeByID(id: Long)
    @Query("DELETE FROM CalendarDayEventEntity")
    suspend fun removeAll()

    @Query("SELECT * from CalendarDayEventEntity WHERE id = :id")
    suspend fun getByID(id: Long) : CalendarDayEventEntity


    @Query("UPDATE CalendarDayEventEntity SET done = 1 WHERE id = :id AND done = 0")
    suspend fun setDone(id: Long)

}