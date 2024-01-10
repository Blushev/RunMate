package com.example.runmate.domain.training

import com.example.runmate.data.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetDistanceTraveledThisWeekByUserIdUseCase {
    suspend operator fun invoke(userId: Int): Flow<Float>
}

class GetDistanceTraveledThisWeekByUserIdUseCaseImpl @Inject constructor(
    private val repository: TrainingRepository
): GetDistanceTraveledThisWeekByUserIdUseCase {
    override suspend fun invoke(userId: Int): Flow<Float> =
        repository.getDistanceTraveledThisWeekByUserId(userId)
}
