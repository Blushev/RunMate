package com.example.runmate.data.repository

import com.example.runmate.data.model.User
import com.example.runmate.data.model.UserGoals
import com.example.runmate.data.model.UserStatistics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val userReference: DatabaseReference = database.getReference("users")


    override fun getUser(userId: String): Flow<User> = flow {
        TODO("Not yet implemented")
    }

    override fun getUserStatistics(userId: String): UserStatistics {
        TODO("Not yet implemented")
    }

    override fun setGoals(userId: String, goals: UserGoals): User {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: User) {
        TODO("Not yet implemented")
    }
}