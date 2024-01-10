package com.example.runmate.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.runmate.data.model.UserModel

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var firstName: String,
    var level: UserLevel = UserLevel.Beginner,
    val createAt: Long,
    var updateAt: Long,
    var isDeleted: Boolean = false,
    var isSelected: Boolean = false
) {
    fun toUserModel(): UserModel =
        UserModel(
            id, firstName, level, createAt, updateAt, isDeleted, isSelected
        )
}