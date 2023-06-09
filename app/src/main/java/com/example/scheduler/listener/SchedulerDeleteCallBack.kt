package com.example.scheduler.listener

import com.example.scheduler.model.ModelSchedule

interface SchedulerDeleteCallBack {
    fun deleteData(schedule : ModelSchedule)

    fun updateData(schedule : ModelSchedule)

    fun inactiveSchedule(schedule : ModelSchedule)

    fun activeSchedule(schedule : ModelSchedule)
}