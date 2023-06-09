package com.example.scheduler.adapter

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.text.format.DateFormat
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.scheduler.R
import com.example.scheduler.base.BaseRecyclerviewAdapter
import com.example.scheduler.databinding.AppListItemsBinding
import com.example.scheduler.listener.SchedulerCallBack
import com.example.scheduler.model.ModelAppData
import java.util.*

class AppListAdapter(private val context: Context, private val dialog: AlertDialog, private val callBack: SchedulerCallBack) :
    BaseRecyclerviewAdapter<ModelAppData, AppListItemsBinding>() {


    override fun getLayout() = R.layout.app_list_items

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<AppListItemsBinding>,
        position: Int
    ) {
        holder.binding.apply {
            val item = items[position]
            model = item
        }

        holder.binding.parent.setOnClickListener {
            dialog.cancel()
            callBack.passData(items[position])
        }

        val drawable = context.packageManager.getApplicationIcon(items[position].packageName)

        holder.binding.appLogo.setImageDrawable(drawable)


    }

}
