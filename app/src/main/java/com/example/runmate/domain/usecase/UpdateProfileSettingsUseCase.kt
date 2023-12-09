package com.example.runmate.domain.usecase

import com.example.runmate.data.model.User
import com.example.runmate.data.model.UserProfileSettings
import com.example.runmate.data.repository.UserRepository
import javax.inject.Inject

class UpdateProfileSettingsUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun execute(userId: String, settings: UserProfileSettings): User {
        val currentUser = userRepository.getUser(userId)

        val updatedUser = currentUser.copy(
            profileSettings = settings
        )

        userRepository.saveUser(updatedUser)

        return updatedUser
    }
}