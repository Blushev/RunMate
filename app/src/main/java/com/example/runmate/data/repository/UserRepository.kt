package com.example.runmate.data.repository

import com.example.runmate.data.db.UserDAO
import com.example.runmate.data.module.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

interface UserRepository {
    suspend fun getUserById(id: UUID): Flow<User>
    suspend fun upsertUser(user: User)
    suspend fun deleteUser(user: User)
}

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDAO
): UserRepository {
    override suspend fun getUserById(id: UUID): Flow<User> {
        return dao.getUserById(id).map { it.toUser() }
    }

    override suspend fun upsertUser(user: User) {
        dao.upsertUser(user.toUserEntity())
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user.toUserEntity())
    }
}
