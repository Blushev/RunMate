package com.example.runmate.domain.user

import com.example.runmate.data.model.UserModel
import com.example.runmate.data.repository.UserRepository
import javax.inject.Inject

interface DeleteUserUseCase {
    suspend operator fun invoke(userModel: UserModel)
}

class DeleteUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): DeleteUserUseCase {
    override suspend fun invoke(userModel: UserModel) =
        repository.deleteUser(userModel)
}
