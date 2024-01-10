package com.example.runmate.domain.training

import com.example.runmate.data.model.TrainingModel
import com.example.runmate.data.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAllTrainingsOrderedByStartAtUseCase {
    suspend operator fun invoke(): Flow<List<TrainingModel>>
}

class GetAllTrainingsOrderedByStartAtUseCaseImpl @Inject constructor(
    private val repository: TrainingRepository
): GetAllTrainingsOrderedByStartAtUseCase {
    override suspend fun invoke(): Flow<List<TrainingModel>> =
        repository.getAllTrainingsOrderedByStartAt
}
