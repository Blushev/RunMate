package com.example.runmate.domain.usecase

import com.example.runmate.data.model.User
import com.example.runmate.data.repository.UserRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun execute(userId: String): User {
        return userRepository.getUser(userId)
    }
}