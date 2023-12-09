package com.example.runmate.domain.usecase

import com.example.runmate.data.model.Workout
import com.example.runmate.data.model.WorkoutDetails
import com.example.runmate.data.repository.WorkoutRepository
import javax.inject.Inject

class StartWorkoutUseCase @Inject constructor(private val workoutRepository: WorkoutRepository) {
    fun execute(userId: String, workoutId: String): Workout {
        val newWorkout = createNewWorkout(userId, workoutId)
        workoutRepository.saveWorkout(newWorkout)
        return newWorkout
    }

    private fun createNewWorkout(userId: String, workoutId: String): Workout {
        val startTime = System.currentTimeMillis() // текущее время в миллисекундах
        val type = "running" // или какой-то другой тип тренировки
        val details = WorkoutDetails(planId = "default", startTime = startTime, endTime = 0, distance = 0.0)

        return Workout(workoutId, userId, type, details)
    }
}