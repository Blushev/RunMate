package com.example.runmate.di.modules

import android.content.Context
import androidx.room.Room
import com.example.runmate.data.db.DataBase
import com.example.runmate.data.db.TrainingDAO
import com.example.runmate.data.db.UserDAO
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDataBase(context: Context): DataBase =
        Room.databaseBuilder(
            context,
            DataBase::class.java,
            "runMate.db"
        ).build()

    @Provides
    fun provideUserDAO(db: DataBase): UserDAO =
        db.userDao

    @Provides
    fun provideTrainingDAO(db: DataBase): TrainingDAO =
        db.trainingDao
}