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

    @Query("select coalesce(sum(distance), 0) from training where strftime('%Y-%W', datetime(startAt/1000, 'unixepoch')) = strftime('%Y-%W', 'now') and userId = :userId")
    fun getDistanceTraveledThisWeekByUserId(userId: Int): Flow<Float>

    @Query("select coalesce(sum(distance), 0) from training where userId = :userId")
    fun getDistanceTraveledByUserId(userId: Int): Flow<Float>

    @Query("select coalesce(sum(endAt - startAt), 0) / 3600000 from training where userId = :userId and endAt is not null")
    fun getTimeTraveledByUserId(userId: Int): Flow<Float>

    @Query("select coalesce(sum(calories), 0) from training where userId = :userId")
    fun getCaloriesTraveledByUserId(userId: Int): Flow<Float>
}