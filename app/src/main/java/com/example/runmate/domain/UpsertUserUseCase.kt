package com.example.runmate.domain

import com.example.runmate.data.module.User
import com.example.runmate.data.repository.UserRepository
import javax.inject.Inject

interface UpsertUserUseCase {
    suspend operator fun invoke(user: User)
}

class UpsertUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): UpsertUserUseCase {
    override suspend fun invoke(user: User) =
        repository.upsertUser(user)
}
