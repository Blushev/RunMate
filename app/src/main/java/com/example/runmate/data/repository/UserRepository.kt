package com.example.runmate.data.repository

import com.example.runmate.data.model.User
import com.example.runmate.data.model.UserGoals
import com.example.runmate.data.model.UserStatistics
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(userId: String): Flow<User>
    fun getUserStatistics(userId: String): UserStatistics
    fun setGoals(userId: String, goals: UserGoals): User
    fun saveUser(user: User)

    // Дополнительные методы для работы с пользователями, если необходимо
}