package com.example.runmate.domain.training

import com.example.runmate.data.model.TrainingModel
import com.example.runmate.data.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetLastUnfinishedTrainingByUserIdUseCase {
    suspend operator fun invoke(userId: Int): Flow<List<TrainingModel>>
}

class GetLastUnfinishedTrainingByUserIdUseCaseImpl @Inject constructor(
    private val repository: TrainingRepository
): GetLastUnfinishedTrainingByUserIdUseCase {
    override suspend fun invoke(userId: Int): Flow<List<TrainingModel>> =
        repository.getLastUnfinishedTrainingByUserId(userId)
}
