package com.example.scheduler.listener

import com.example.scheduler.model.ModelAppData

interface SchedulerCallBack {
    fun passData(appData : ModelAppData)
}