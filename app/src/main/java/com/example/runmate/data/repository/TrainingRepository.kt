package com.example.runmate.data.repository

import com.example.runmate.data.db.TrainingDAO
import com.example.runmate.data.model.TrainingModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface TrainingRepository {
    suspend fun getTrainingById(id: Int): Flow<List<TrainingModel>>
    suspend fun getTrainingsOrderedByStartAtLimited(limit: Int): Flow<List<TrainingModel>>
    suspend fun upsertTraining(trainingModel: TrainingModel)
    suspend fun deleteTraining(trainingModel: TrainingModel)

    val getAllTrainingsOrderedByStartAt: Flow<List<TrainingModel>>
}

class TrainingRepositoryImpl @Inject constructor(
    private val dao: TrainingDAO
): TrainingRepository {

    override suspend fun getTrainingById(id: Int): Flow<List<TrainingModel>> {
        return dao.getTrainingById(id).map { it -> it.map { it.toTrainingModel() } }
    }

    override suspend fun getTrainingsOrderedByStartAtLimited(limit: Int): Flow<List<TrainingModel>> {
        return dao.getTrainingsOrderedByStartAtLimited(limit).map { it ->
            it.map {
                it.toTrainingModel()
            }
        }
    }

    override suspend fun upsertTraining(trainingModel: TrainingModel) {
        return dao.upsertTraining(trainingModel.toTrainingEntity())
    }

    override suspend fun deleteTraining(trainingModel: TrainingModel) {
        return dao.deleteTraining(trainingModel.toTrainingEntity())
    }

    override val getAllTrainingsOrderedByStartAt: Flow<List<TrainingModel>>
        get() = dao.getAllTrainingsOrderedByStartAt().map { it ->
            it.map {
                it.toTrainingModel()
            }
        }
}
