package com.example.runmate.domain.training

import com.example.runmate.data.model.TrainingModel
import com.example.runmate.data.repository.TrainingRepository
import javax.inject.Inject

interface UpsertTrainingUseCase {
    suspend operator fun invoke(trainingModel: TrainingModel)
}

class UpsertTrainingUseCaseImpl @Inject constructor(
    private val repository: TrainingRepository
): UpsertTrainingUseCase {
    override suspend fun invoke(trainingModel: TrainingModel) =
        repository.upsertTraining(trainingModel)
}
