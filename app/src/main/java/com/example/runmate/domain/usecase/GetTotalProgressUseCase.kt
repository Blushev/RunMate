package com.example.runmate.domain.usecase

import com.example.runmate.data.model.UserStatistics
import com.example.runmate.data.repository.UserRepository
import javax.inject.Inject

class GetTotalProgressUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun execute(userId: String): UserStatistics {
        return userRepository.getUserStatistics(userId)
    }
}