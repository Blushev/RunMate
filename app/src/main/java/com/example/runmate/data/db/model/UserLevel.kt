package com.example.runmate.data.db.model

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters(UserLevel.Converter::class)
enum class UserLevel {
    Beginner,
    Intermediate,
    Advanced;

    class Converter {
        @TypeConverter
        fun toUserLevel(value: String): UserLevel = enumValueOf(value)

        @TypeConverter
        fun fromUserLevel(value: UserLevel) = value.name
    }
}