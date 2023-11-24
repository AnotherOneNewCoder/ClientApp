package ru.zhogin.composeClientApp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zhogin.composeClientApp.dao.ClientDao
import ru.zhogin.composeClientApp.entity.ClientEntity


@Database(
    entities = [
        ClientEntity::class
    ],
    version = 1
)
abstract class AppDb: RoomDatabase() {
    abstract fun clientDao(): ClientDao
}