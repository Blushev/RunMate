package com.example.runmate.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.runmate.data.module.User
import java.util.UUID

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: UUID,
    var login: String,
    var firstName: String,
    var lastName: String?,
    var secondName: String?,
    var createAt: Long
) {
    fun toUser(): User =
        User(
            id, login, firstName, lastName, secondName, createAt
        )
}