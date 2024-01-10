package com.example.runmate.domain.user

import com.example.runmate.data.model.UserModel
import com.example.runmate.data.repository.UserRepository
import javax.inject.Inject

interface UpsertUserUseCase {
    suspend operator fun invoke(userModel: UserModel)
}

class UpsertUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): UpsertUserUseCase {
    override suspend fun invoke(userModel: UserModel) =
        repository.upsertUser(userModel)
}
