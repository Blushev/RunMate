package com.example.runmate.domain

import com.example.runmate.data.module.User
import com.example.runmate.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

interface SubscribeUserUseCase {
    suspend operator fun invoke(id: UUID): Flow<User>
}

class SubscribeUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): SubscribeUserUseCase {
    override suspend operator fun invoke(id: UUID): Flow<User> =
        repository.getUserById(id)
}
