package com.example.runmate.data.repository

import com.example.runmate.data.db.UserDAO
import com.example.runmate.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserRepository {
    suspend fun upsertUser(userModel: UserModel)
    suspend fun deleteUser(userModel: UserModel)

    val getLatestSelectedUser: Flow<List<UserModel>>
    val getAllUsersOrderedByCreateAt: Flow<List<UserModel>>
}

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDAO
): UserRepository {

    override suspend fun upsertUser(userModel: UserModel) {
        dao.upsertUser(userModel.toUserEntity())
    }

    override suspend fun deleteUser(userModel: UserModel) {
        dao.deleteUser(userModel.toUserEntity())
    }

    override val getLatestSelectedUser: Flow<List<UserModel>>
        get() = dao.getLatestSelectedUser().map { it -> it.map { it.toUserModel() } }
    override val getAllUsersOrderedByCreateAt: Flow<List<UserModel>>
        get() = dao.getAllUsersOrderedByCreateAt().map { it ->
            it.map {
                it.toUserModel()
            }
        }
}
