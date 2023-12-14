package com.example.runmate.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.runmate.data.db.model.UserEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface UserDAO {

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("select * from user where id = :id")
    fun getUserById(id: UUID) : Flow<UserEntity>
}