package com.example.runmate.domain.training

import com.example.runmate.data.model.TrainingModel
import com.example.runmate.data.repository.TrainingRepository
import javax.inject.Inject

interface DeleteTrainingUseCase {
    suspend operator fun invoke(trainingModel: TrainingModel)
}

class DeleteTrainingUseCaseImpl @Inject constructor(
    private val repository: TrainingRepository
): DeleteTrainingUseCase {
    override suspend fun invoke(trainingModel: TrainingModel) =
        repository.deleteTraining(trainingModel)
}
