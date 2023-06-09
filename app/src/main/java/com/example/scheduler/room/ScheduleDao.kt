package com.example.scheduler.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.scheduler.model.ModelSchedule
import kotlinx.coroutines.flow.Flow

interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: ModelSchedule)

    @Query("SELECT * FROM schedule ORDER BY id ASC")
    fun getScheduleList(): Flow<List<ModelSchedule>>

    @Query("SELECT EXISTS(SELECT * FROM schedule WHERE scheduleTimeInWord = :time)")
    suspend fun doesDataExist(time: String): Boolean

    @Delete
    fun deleteSchedule(model: ModelSchedule)

    @Query("UPDATE schedule SET scheduleTime = :time,scheduleTimeInWord = :timeInWord,status = :status  WHERE id = :id")
    fun update(time: Long, timeInWord: String, id: Int, status: Boolean)

    @Query("DELETE FROM schedule")
    suspend fun clearScheduleTable()
}