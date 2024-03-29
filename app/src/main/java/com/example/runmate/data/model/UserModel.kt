package com.example.runmate.data.model

import com.example.runmate.data.db.model.UserEntity
import com.example.runmate.data.db.model.UserLevel

data class UserModel(
    val id: Int = 0,
    var firstName: String,
    var level: UserLevel = UserLevel.Beginner,
    var goal: Float = 50f,
    val createAt: Long,
    var updateAt: Long,
    var isDeleted: Boolean,
    var isSelected: Boolean
) {
    fun toUserEntity(): UserEntity =
        UserEntity(
            id, firstName, level, goal, createAt, updateAt, isDeleted, isSelected
        )
}
