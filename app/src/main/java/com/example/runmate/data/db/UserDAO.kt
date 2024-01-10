package com.example.runmate.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.runmate.data.db.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("select * from user where isSelected order by updateAt desc limit 1")
    fun getLatestSelectedUser(): Flow<List<UserEntity>>

    @Query("select * from user order by createAt desc")
    fun getAllUsersOrderedByCreateAt(): Flow<List<UserEntity>>
}