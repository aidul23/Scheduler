package com.example.scheduler.ui

import com.example.scheduler.model.ModelSchedule
import com.example.scheduler.room.ScheduleDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val scheduleDao: ScheduleDao) {

    val getScheduleData: Flow<List<ModelSchedule>> = scheduleDao.getScheduleList()

}