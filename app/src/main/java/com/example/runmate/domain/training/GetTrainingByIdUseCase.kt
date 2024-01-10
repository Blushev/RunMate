package com.example.runmate.domain.training

import com.example.runmate.data.model.TrainingModel
import com.example.runmate.data.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTrainingByIdUseCase {
    suspend operator fun invoke(id: Int): Flow<List<TrainingModel>>
}

class GetTrainingByIdUseCaseImpl @Inject constructor(
    private val repository: TrainingRepository
): GetTrainingByIdUseCase {
    override suspend fun invoke(id: Int): Flow<List<TrainingModel>> =
        repository.getTrainingById(id)
}
