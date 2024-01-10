package com.example.runmate.domain.training

import com.example.runmate.data.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTimeTraveledByUserIdUseCase {
    suspend operator fun invoke(userId: Int): Flow<Float>
}

class GetTimeTraveledByUserIdUseCaseImpl @Inject constructor(
    private val repository: TrainingRepository
): GetTimeTraveledByUserIdUseCase {
    override suspend fun invoke(userId: Int) =
        repository.getTimeTraveledByUserId(userId)
}
