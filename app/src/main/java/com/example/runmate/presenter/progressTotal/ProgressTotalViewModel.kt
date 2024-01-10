package com.example.runmate.presenter.progressTotal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runmate.domain.training.GetCaloriesTraveledByUserIdUseCase
import com.example.runmate.domain.training.GetDistanceTraveledByUserIdUseCase
import com.example.runmate.domain.training.GetTimeTraveledByUserIdUseCase
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProgressTotalViewModel @Inject constructor(
    private val getDistanceTraveledByUserIdUseCase: GetDistanceTraveledByUserIdUseCase,
    private val getTimeTraveledByUserIdUseCase: GetTimeTraveledByUserIdUseCase,
    private val getCaloriesTraveledByUserIdUseCase: GetCaloriesTraveledByUserIdUseCase
): ViewModel() {
    private val _liveDistanceTraveledData = MutableLiveData<Float>()
    val liveDistanceTraveledData: LiveData<Float>
        get() = _liveDistanceTraveledData

    private val _liveTimeTraveledData = MutableLiveData<Float>()
    val liveTimeTraveledData: LiveData<Float>
        get() = _liveTimeTraveledData

    private val _liveCaloriesTraveledData = MutableLiveData<Float>()
    val liveCaloriesTraveledData: LiveData<Float>
        get() = _liveCaloriesTraveledData

//    fun getLiveDistanceTraveledData(userId: Int)

    fun getLiveData(userId: Int) {
        viewModelScope.launch {
            val distanceFlow = getDistanceTraveledByUserIdUseCase(userId)
            val timeFlow = getTimeTraveledByUserIdUseCase(userId)
            val caloriesFlow = getCaloriesTraveledByUserIdUseCase(userId)

            combine(distanceFlow, timeFlow, caloriesFlow) { distance, time, calories ->
                _liveDistanceTraveledData.postValue(distance)
                _liveTimeTraveledData.postValue(time)
                _liveCaloriesTraveledData.postValue(calories)

            }.collect {}
        }
    }
}