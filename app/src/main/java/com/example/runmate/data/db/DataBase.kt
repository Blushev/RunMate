package com.example.runmate.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.runmate.data.db.model.TrainingEntity
import com.example.runmate.data.db.model.UserEntity

@Database(
    entities = [
        TrainingEntity::class,
        UserEntity::class
    ],
    version = 1
)
abstract class DataBase: RoomDatabase() {
    abstract val userDao: UserDAO
    abstract val trainingDao: TrainingDAO
}