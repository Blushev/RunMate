package com.example.runmate.presenter.progressWeek

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runmate.domain.training.GetDistanceTraveledThisWeekByUserIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProgressWeekViewModel @Inject constructor(
    private val getDistanceTraveledThisWeekByUserIdUseCase: GetDistanceTraveledThisWeekByUserIdUseCase
): ViewModel() {
    private val _liveTraveledDistanceData = MutableLiveData<Float>()
    val liveTraveledDistanceData: LiveData<Float>
        get() = _liveTraveledDistanceData

    fun getTraveledDistance(userId: Int) {
        viewModelScope.launch {
            getDistanceTraveledThisWeekByUserIdUseCase(userId).collect {
                _liveTraveledDistanceData.postValue(it)
            }
        }
    }
}