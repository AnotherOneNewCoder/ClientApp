package ru.zhogin.composeClientApp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.zhogin.composeClientApp.dto.CalendarDay
import ru.zhogin.composeClientApp.repositories.CalendarDayRepository
import java.time.LocalDate
import javax.inject.Inject


private val emptyDay = CalendarDay(
    id = 0L,
    date = "",
)

private val selectedDay = LocalDate.now()

@HiltViewModel
class CalendarDayViewModel @Inject constructor(
    private val repository: CalendarDayRepository,
) : ViewModel() {
    val data = repository.data
    val editedDay = MutableLiveData(emptyDay)
    val editedSelectedDay = MutableLiveData(selectedDay)

    fun saveDay() {
        editedDay.value?.let { calendarDay ->
            viewModelScope.launch {
                try {
                    repository.save(calendarDay)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        editedDay.value = emptyDay
    }

    fun removeDayById(id: Long) = viewModelScope.launch {
        try {
            repository.removeById(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun clearData() {
        editedDay.value = emptyDay
    }

    fun setWeekend(id: Long) = viewModelScope.launch {
        try {
            repository.isWeekend(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setWorkingDay(id: Long) = viewModelScope.launch {
        try {
            repository.isWorkingDay(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun changeSelectedDay(date: LocalDate) {
        editedSelectedDay.value = date
    }
}