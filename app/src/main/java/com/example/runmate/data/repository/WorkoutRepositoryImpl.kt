package com.example.runmate.data.repository

import com.example.runmate.data.model.Workout
import com.example.runmate.data.model.WorkoutDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.Flow
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor() : WorkoutRepository {

    override fun getWorkouts(userId: String): List<Workout> {
        TODO("Not yet implemented")
    }

    override fun getWorkoutHistory(userId: String): List<Workout> {
        TODO("Not yet implemented")
    }

    override fun saveWorkout(workout: Workout) {
        TODO("Not yet implemented")
    }

    override fun getWorkout(userId: String, workoutId: String): Workout {
        TODO("Not yet implemented")
    }

    override fun updateWorkout(workout: Workout) {
        TODO("Not yet implemented")
    }
}