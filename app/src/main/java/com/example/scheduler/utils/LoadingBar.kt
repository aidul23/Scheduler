package com.example.scheduler.utils

import android.app.Activity
import android.app.Dialog
import com.example.scheduler.R
import javax.inject.Inject

class LoadingBar @Inject constructor(activity: Activity) {

    var dialog:Dialog = Dialog(activity)

    fun showDialog() {
        dialog.apply {
            setCancelable(true)
            setContentView(R.layout.loading_dialog)
            show()
        }
    }

    fun cancelDialog(){
        dialog.dismiss()
    }
}