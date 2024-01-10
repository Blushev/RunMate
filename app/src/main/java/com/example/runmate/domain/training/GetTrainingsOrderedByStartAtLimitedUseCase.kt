package com.example.runmate.domain.training

import com.example.runmate.data.model.TrainingModel
import com.example.runmate.data.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTrainingsOrderedByStartAtLimitedUseCase {
    suspend operator fun invoke(limit: Int): Flow<List<TrainingModel>>
}

class GetTrainingsOrderedByStartAtLimitedUseCaseImpl @Inject constructor(
    private val repository: TrainingRepository
): GetTrainingsOrderedByStartAtLimitedUseCase {
    override suspend fun invoke(limit: Int): Flow<List<TrainingModel>> =
        repository.getTrainingsOrderedByStartAtLimited(limit)
}
