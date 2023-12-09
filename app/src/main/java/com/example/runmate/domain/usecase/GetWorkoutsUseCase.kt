package com.example.runmate.domain.usecase

import com.example.runmate.data.model.Workout
import com.example.runmate.data.repository.WorkoutRepository
import javax.inject.Inject

class GetWorkoutsUseCase @Inject constructor(private val workoutRepository: WorkoutRepository) {
    fun execute(userId: String): List<Workout> {
        return workoutRepository.getWorkouts(userId)
    }
}