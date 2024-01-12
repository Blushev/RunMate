package com.example.runmate.presenter.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runmate.data.model.TrainingModel
import com.example.runmate.domain.training.UpsertTrainingUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrainingViewModel @Inject constructor(
    private val upsertTrainingUseCase: UpsertTrainingUseCase
): ViewModel() {

    fun upsertTraining(trainingModel: TrainingModel, userId: Int) {
        viewModelScope.launch {
            val training = trainingModel.copy(
                userId = userId,
                endAt = System.currentTimeMillis()
            )
            upsertTrainingUseCase.invoke(training)
        }
    }
}