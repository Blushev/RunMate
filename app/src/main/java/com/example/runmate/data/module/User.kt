package com.example.runmate.data.module

import com.example.runmate.data.db.model.UserEntity
import java.util.UUID

data class User(
    var id: UUID,
    var login: String,
    var firstName: String,
    var lastName: String?,
    var secondName: String?,
    var createAt: Long
) {
    fun toUserEntity(): UserEntity =
        UserEntity(
            id, login, firstName, lastName, secondName, createAt
        )
}
