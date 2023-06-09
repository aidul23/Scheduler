package com.example.scheduler.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.scheduler.model.ModelSchedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    val homeRepository: MainRepository
) : ViewModel() {
    val getScheduleData : LiveData<List<ModelSchedule>> = homeRepository.getScheduleData.flowOn(
        Dispatchers.Main)
        .asLiveData(context =  viewModelScope.coroutineContext)
}