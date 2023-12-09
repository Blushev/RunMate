package com.example.runmate.domain.usecase

import com.example.runmate.data.model.Workout
import com.example.runmate.data.model.WorkoutDetails
import com.example.runmate.data.repository.WorkoutRepository
import javax.inject.Inject

class StopWorkoutUseCase @Inject constructor(private val workoutRepository: WorkoutRepository) {
    fun execute(userId: String, workoutId: String, workoutData: WorkoutDetails): Workout {
        val stoppedWorkout = stopWorkout(userId, workoutId, workoutData)
        workoutRepository.updateWorkout(stoppedWorkout)
        return stoppedWorkout
    }

    private fun stopWorkout(userId: String, workoutId: String, workoutData: WorkoutDetails): Workout {
        val currentWorkout = workoutRepository.getWorkout(userId, workoutId)

        val endTime = System.currentTimeMillis()
        val stoppedWorkout = currentWorkout.copy(
            details = workoutData.copy(endTime = endTime),
            //
        )

        return stoppedWorkout
    }
}

