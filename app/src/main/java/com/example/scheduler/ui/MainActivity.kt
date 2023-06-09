package com.example.scheduler.ui

import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.TimePicker
import com.example.scheduler.R
import com.example.scheduler.adapter.SchedulerAdapter
import com.example.scheduler.base.BaseActivity
import com.example.scheduler.databinding.ActivityMainBinding
import com.example.scheduler.listener.SchedulerDeleteCallBack
import com.example.scheduler.model.ModelAppData
import com.example.scheduler.model.ModelSchedule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener,
    SchedulerDeleteCallBack {

    var ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469

    lateinit var builder: AlertDialog.Builder
    lateinit var alert: AlertDialog

    override fun getView() = R.layout.activity_main

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (!Settings.canDrawOverlays(this)) {
                permissionDialog()
            }
        }


        mainViewModel.getScheduleData.observe(this) {
            val adapter = SchedulerAdapter(this, this)
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.adapter = adapter
            adapter.addItems(it)
        }

        binding.addSchedule.setOnClickListener {
            com.example.scheduler.utils.Navigator.sharedInstance.navigate(this, SchedulerActivity::class.java)
        }

    }

    override fun deleteData(schedule: ModelSchedule) {
        addToSchedule.cancelSchedule(schedule.scheduleTime.toString())
        schedulerViewModel.delete(schedule)
    }

    override fun updateData(schedule: ModelSchedule) {
        previosScheduleTime = schedule.scheduleTime
        appData = ModelAppData(schedule.id,schedule.appName,schedule.packageName)
        showDatePicker(true)
    }

    override fun inactiveSchedule(schedule: ModelSchedule) {
        schedule.status = false
        addToSchedule.cancelSchedule(schedule.scheduleTime.toString())
        schedulerViewModel.update(schedule)
    }

    override fun activeSchedule(schedule: ModelSchedule) {
        schedule.status = true
        addToSchedule.addToQueue(schedule.scheduleTime, schedule.packageName)
        schedulerViewModel.update(schedule)
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                // You don't have permission
                checkPermission()
            }
        }
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE)
            }
        }
    }

    private fun permissionDialog(){
        builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder
            .setTitle("Permission Alert !!")
            .setMessage("Allow Display over other apps permission to work on Android 10 or higher")
            .setPositiveButton("Grant") { dialog, id ->
                checkPermission()
            }
            .setNegativeButton("Not Now") { dialog, id ->
                dialog.cancel()
            }

        alert = builder.create()
        alert.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        TODO("Not yet implemented")
    }
}