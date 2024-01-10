package com.example.runmate.domain.training

import com.example.runmate.data.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetDistanceTraveledByUserIdUseCase {
    suspend operator fun invoke(userId: Int): Flow<Float>
}

class GetDistanceTraveledByUserIdUseCaseImpl @Inject constructor(
    private val repository: TrainingRepository
): GetDistanceTraveledByUserIdUseCase {
    override suspend fun invoke(userId: Int): Flow<Float> =
        repository.getDistanceTraveledByUserId(userId)
}
