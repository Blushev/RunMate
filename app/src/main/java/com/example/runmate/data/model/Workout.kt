package com.example.runmate.data.model

data class Workout(
    val workoutId: String,
    val userId: String,
    val type: String,
    val details: WorkoutDetails
)
