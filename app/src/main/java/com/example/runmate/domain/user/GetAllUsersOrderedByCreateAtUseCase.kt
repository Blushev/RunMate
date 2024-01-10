package com.example.runmate.domain.user

import com.example.runmate.data.model.UserModel
import com.example.runmate.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAllUsersOrderedByCreateAtUseCase {
    suspend operator fun invoke(): Flow<List<UserModel>>
}

class GetAllUsersOrderedByCreateAtUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): GetAllUsersOrderedByCreateAtUseCase {
    override suspend fun invoke(): Flow<List<UserModel>> =
        repository.getAllUsersOrderedByCreateAt
}
