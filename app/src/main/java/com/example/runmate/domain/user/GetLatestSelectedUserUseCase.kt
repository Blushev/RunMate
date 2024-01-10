package com.example.runmate.domain.user

import com.example.runmate.data.model.UserModel
import com.example.runmate.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetLatestSelectedUserUseCase {
    suspend operator fun invoke(): Flow<List<UserModel>>
}

class GetLatestSelectedUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): GetLatestSelectedUserUseCase {
    override suspend fun invoke(): Flow<List<UserModel>> =
        repository.getLatestSelectedUser
}
