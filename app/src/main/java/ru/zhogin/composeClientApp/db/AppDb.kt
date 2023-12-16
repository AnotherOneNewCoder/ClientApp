package ru.zhogin.composeClientApp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zhogin.composeClientApp.dao.CalendarDayDao
import ru.zhogin.composeClientApp.dao.CalendarDayEventDao
import ru.zhogin.composeClientApp.dao.ClientDao
import ru.zhogin.composeClientApp.entity.CalendarDayEntity
import ru.zhogin.composeClientApp.entity.CalendarDayEventEntity
import ru.zhogin.composeClientApp.entity.ClientEntity


@Database(
    entities = [
        ClientEntity::class,
        CalendarDayEntity::class,
        CalendarDayEventEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class AppDb: RoomDatabase() {
    abstract fun clientDao(): ClientDao
    abstract fun calendarDayDao(): CalendarDayDao
    abstract fun calendarDayEventDao(): CalendarDayEventDao
}