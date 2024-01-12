package com.example.runmate.presenter.running

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runmate.data.model.TrainingModel
import com.example.runmate.data.model.UserModel
import com.example.runmate.domain.training.GetLastUnfinishedTrainingByUserIdUseCase
import com.example.runmate.domain.training.UpsertTrainingUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrainingViewModel @Inject constructor(
    private val getLastUnfinishedTrainingByUserIdUseCase: GetLastUnfinishedTrainingByUserIdUseCase,
    private val upsertTrainingUseCase: UpsertTrainingUseCase
): ViewModel() {

    private val _livaCurrentTrainingData = MutableLiveData<TrainingModel>()
    val livaCurrentTrainingData: LiveData<TrainingModel>
        get() = _livaCurrentTrainingData

    fun getCurrentTrainingData(userModel: UserModel) {
        viewModelScope.launch {
            getLastUnfinishedTrainingByUserIdUseCase(userModel.id).collect {
                if (it.isNotEmpty()) {
                    _livaCurrentTrainingData.postValue(it[0])
                }
            }
        }
    }

    fun finishTraining() {
        viewModelScope.launch {
            var training = _livaCurrentTrainingData.value
            training = training?.copy(
                endAt = System.currentTimeMillis()
            )
            if (training != null) {
                upsertTrainingUseCase(training)
            }
        }
    }

    fun updateCurrentTrainingData(distance: Float, calories: Float, averageSpeed: Float) {
        viewModelScope.launch {
            var training = _livaCurrentTrainingData.value
            training = training?.copy(
                distance = distance,
                calories = calories,
                averageSpeed = averageSpeed
            )
            if (training != null) {
                upsertTrainingUseCase(training)
            }
        }
    }
}