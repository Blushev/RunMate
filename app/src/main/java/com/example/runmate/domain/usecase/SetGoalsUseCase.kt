package com.example.runmate.domain.usecase

import com.example.runmate.data.model.User
import com.example.runmate.data.model.UserGoals
import com.example.runmate.data.repository.UserRepository
import javax.inject.Inject

class SetGoalsUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun execute(userId: String, goals: UserGoals): User {
        return userRepository.setGoals(userId, goals)
    }
}