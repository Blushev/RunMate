package com.example.runmate.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.runmate.data.db.model.TrainingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDAO {

    @Upsert
    suspend fun upsertTraining(training: TrainingEntity)

    @Delete
    suspend fun deleteTraining(training: TrainingEntity)

    @Query("select * from training where id = :id")
    fun getTrainingById(id: Int): Flow<List<TrainingEntity>>

    @Query("select * from training order by startAt desc")
    fun getAllTrainingsOrderedByStartAt(): Flow<List<TrainingEntity>>

    @Query("select * from training order by startAt desc limit :limit")
    fun getTrainingsOrderedByStartAtLimited(limit: Int): Flow<List<TrainingEntity>>
}