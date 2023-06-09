package com.example.scheduler.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduler.model.ModelSchedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SchedulerViewModel
@Inject
constructor(private val schedulerRepository: SchedulerRepository) : ViewModel() {

    fun insert(context: Context, schedule: ModelSchedule) {
        viewModelScope.launch {
            val isExists = schedulerRepository.availableData(schedule.scheduleTimeInWord)
            if (!isExists) {
                schedulerRepository.insert(schedule)
            } else {
                Toast.makeText(context, "This schedule time is already taken", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun delete(schedule: ModelSchedule) {
        viewModelScope.launch {
            schedulerRepository.deleteSchedule(schedule)
        }
    }

    fun update(schedule: ModelSchedule) {
        viewModelScope.launch {
            schedulerRepository.updateSchedule(schedule)
        }
    }

}