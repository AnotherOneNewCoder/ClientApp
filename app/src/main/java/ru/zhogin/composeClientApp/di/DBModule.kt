package ru.zhogin.composeClientApp.di

import android.content.Context
import androidx.room.Room

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.zhogin.composeClientApp.dao.CalendarDayDao
import ru.zhogin.composeClientApp.dao.CalendarDayEventDao
import ru.zhogin.composeClientApp.dao.ClientDao
import ru.zhogin.composeClientApp.db.AppDb
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DBModule {
    @Singleton
    @Provides
    fun provideDb(
        @ApplicationContext
        context: Context
    ): AppDb = Room.databaseBuilder(context, AppDb::class.java, "app_db")
        .build()

    @Provides
    fun providesClientDao(
        appDb: AppDb
    ): ClientDao = appDb.clientDao()

    @Provides
    fun providesCalendarDayDao(
        appDb: AppDb
    ): CalendarDayDao = appDb.calendarDayDao()

    @Provides
    fun providesCalendarDayEventDao(
        appDb: AppDb
    ): CalendarDayEventDao = appDb.calendarDayEventDao()

}