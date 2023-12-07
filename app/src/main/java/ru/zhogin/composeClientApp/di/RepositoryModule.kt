package ru.zhogin.composeClientApp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.zhogin.composeClientApp.repositories.CalendarDayEventRepository
import ru.zhogin.composeClientApp.repositories.CalendarDayEventRepositoryImpl
import ru.zhogin.composeClientApp.repositories.CalendarDayRepository
import ru.zhogin.composeClientApp.repositories.CalendarDayRepositoryImpl
import ru.zhogin.composeClientApp.repositories.ClientRepository
import ru.zhogin.composeClientApp.repositories.ClientRepositoryImpl
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsClientRepository(impl: ClientRepositoryImpl): ClientRepository

    @Singleton
    @Binds
    fun bindsCalendarDayRepository(impl: CalendarDayRepositoryImpl): CalendarDayRepository
    @Singleton
    @Binds
    fun bindsCalendarDayEventRepository(impl: CalendarDayEventRepositoryImpl): CalendarDayEventRepository
}

