package com.example.runmate.domain.training

import com.example.runmate.data.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCaloriesTraveledByUserIdUseCase {
    suspend operator fun invoke(userId: Int): Flow<Float>
}

class GetCaloriesTraveledByUserIdUseCaseImpl @Inject constructor(
    private val repository: TrainingRepository
): GetCaloriesTraveledByUserIdUseCase {
    override suspend fun invoke(userId: Int): Flow<Float> =
        repository.getCaloriesTraveledByUserId(userId)
}
