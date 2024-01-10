package com.example.runmate.presenter.eventList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runmate.data.model.TrainingModel
import com.example.runmate.domain.training.GetAllTrainingsOrderedByStartAtUseCase
import com.example.runmate.domain.training.GetTrainingsOrderedByStartAtLimitedUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventsViewModel @Inject constructor(
    private val getAllTrainingsOrderedByStartAtUseCase: GetAllTrainingsOrderedByStartAtUseCase,
    private val getAllTrainingsOrderedByStartAtLimitedUseCase: GetTrainingsOrderedByStartAtLimitedUseCase
): ViewModel() {
    private val _events = MutableLiveData<List<TrainingModel>>()
    val events: LiveData<List<TrainingModel>>
        get() = _events

    fun getAllEvents() {
        viewModelScope.launch {
            getAllTrainingsOrderedByStartAtUseCase().collect {
                _events.postValue(it)
            }
        }
    }

    fun getEventsLimited(limit: Int) {
        viewModelScope.launch {
            getAllTrainingsOrderedByStartAtLimitedUseCase(limit).collect {
                _events.postValue(it)
            }
        }
    }
}