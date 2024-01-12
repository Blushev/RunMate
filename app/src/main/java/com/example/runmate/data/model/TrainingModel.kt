package com.example.runmate.data.model

import com.example.runmate.data.db.model.TrainingEntity

data class TrainingModel(
    val id: Int = 0,
    val userId: Int = 0,
    val startAt: Long,
    var endAt: Long? = null,
    var distance: Float,
    var calories: Float,
    var averageSpeed: Float
) {
    fun toTrainingEntity(): TrainingEntity =
        TrainingEntity(
            id, userId, startAt, endAt, distance, calories, averageSpeed
        )
}
