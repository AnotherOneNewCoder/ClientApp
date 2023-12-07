package ru.zhogin.composeClientApp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.zhogin.composeClientApp.dto.CalendarDayEvent
import ru.zhogin.composeClientApp.dto.ColorType
import ru.zhogin.composeClientApp.repositories.CalendarDayEventRepository
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject


private val emptyDayEvent = CalendarDayEvent(
    id = 0L,
    date = LocalDate.parse("2000-01-01"),
    name = "",
    color = ColorType.WHITE,
    start = LocalDateTime.parse("2000-01-01T00:00:00"),
    end = LocalDateTime.parse("2000-01-01T00:00:00"),
    clientId = 0L,
)
@HiltViewModel
class CalendarDayEventViewModel @Inject constructor(
    private val repository: CalendarDayEventRepository,
): ViewModel() {

    val data = repository.data
    val editedDayEvent = MutableLiveData(emptyDayEvent)

    fun saveDayEvent() = editedDayEvent.value?.let { calendarDayEvent ->
        viewModelScope.launch {
            try {
                repository.saveDayEvent(calendarDayEvent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        editedDayEvent.value = emptyDayEvent
    }
    fun removeDayEventById(id: Long) = viewModelScope.launch {
        try {
            repository.removeById(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun clearData() {
        editedDayEvent.value = emptyDayEvent
    }
    fun setDone(id: Long) = viewModelScope.launch {
        try {
            repository.setDone(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}