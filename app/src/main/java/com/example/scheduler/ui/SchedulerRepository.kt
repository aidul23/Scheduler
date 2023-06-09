package com.example.scheduler.ui

import com.example.scheduler.model.ModelSchedule
import com.example.scheduler.room.ScheduleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SchedulerRepository @Inject constructor(private val scheduleDao: ScheduleDao) {

    suspend fun insert(schedule: ModelSchedule) = withContext(Dispatchers.IO)
    {
        scheduleDao.insert(schedule)
    }

    suspend fun availableData(time: String): Boolean =
        scheduleDao.doesDataExist(time)

    suspend fun deleteSchedule(schedule: ModelSchedule) = withContext(Dispatchers.IO) {
        scheduleDao.deleteSchedule(schedule)
    }

    suspend fun updateSchedule(schedule: ModelSchedule) = withContext(Dispatchers.IO) {
        scheduleDao.update(schedule.scheduleTime,schedule.scheduleTimeInWord, schedule.id,schedule.status)
    }

}