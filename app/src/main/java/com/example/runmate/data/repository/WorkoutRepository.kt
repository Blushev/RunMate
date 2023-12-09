package com.example.runmate.data.repository

import com.example.runmate.data.model.Workout

interface WorkoutRepository {
    fun getWorkouts(userId: String): List<Workout>
    fun getWorkoutHistory(userId: String): List<Workout>
    fun saveWorkout(workout: Workout)
    fun getWorkout(userId: String, workoutId: String): Workout
    fun updateWorkout(workout: Workout)

    // Дополнительные методы для работы с тренировками, если необходимо
}