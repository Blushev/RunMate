package com.example.runmate.data.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.runmate.data.model.TrainingModel

@Entity(
    tableName = "training",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"]
    )],
    indices = [Index("userId")]
)
data class TrainingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val startAt: Long,
    var endAt: Long?,
    var distance: Float = 0f,
    var calories: Float = 0f,
    var averageSpeed: Float = 0f
) {
    fun toTrainingModel(): TrainingModel =
        TrainingModel(
            id, userId, startAt, endAt, distance, calories, averageSpeed
        )
}