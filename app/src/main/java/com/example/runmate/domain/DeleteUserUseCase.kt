package com.example.runmate.domain

import com.example.runmate.data.module.User
import com.example.runmate.data.repository.UserRepository
import javax.inject.Inject

interface DeleteUserUseCase {
    suspend operator fun invoke(user: User)
}

class DeleteUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): DeleteUserUseCase {
    override suspend fun invoke(user: User) =
        repository.deleteUser(user)
}
