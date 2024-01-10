package com.example.runmate.di.modules

import android.app.Application
import android.content.Context
import com.example.runmate.data.repository.TrainingRepository
import com.example.runmate.data.repository.TrainingRepositoryImpl
import com.example.runmate.data.repository.UserRepository
import com.example.runmate.data.repository.UserRepositoryImpl
import com.example.runmate.domain.training.DeleteTrainingUseCase
import com.example.runmate.domain.training.DeleteTrainingUseCaseImpl
import com.example.runmate.domain.training.GetAllTrainingsOrderedByStartAtUseCase
import com.example.runmate.domain.training.GetAllTrainingsOrderedByStartAtUseCaseImpl
import com.example.runmate.domain.training.GetTrainingByIdUseCase
import com.example.runmate.domain.training.GetTrainingByIdUseCaseImpl
import com.example.runmate.domain.training.GetTrainingsOrderedByStartAtLimitedUseCase
import com.example.runmate.domain.training.GetTrainingsOrderedByStartAtLimitedUseCaseImpl
import com.example.runmate.domain.training.UpsertTrainingUseCase
import com.example.runmate.domain.training.UpsertTrainingUseCaseImpl
import com.example.runmate.domain.user.DeleteUserUseCase
import com.example.runmate.domain.user.DeleteUserUseCaseImpl
import com.example.runmate.domain.user.GetAllUsersOrderedByCreateAtUseCase
import com.example.runmate.domain.user.GetAllUsersOrderedByCreateAtUseCaseImpl
import com.example.runmate.domain.user.GetLatestSelectedUserUseCase
import com.example.runmate.domain.user.GetLatestSelectedUserUseCaseImpl
import com.example.runmate.domain.user.UpsertUserUseCase
import com.example.runmate.domain.user.UpsertUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AppBindsModule {

    @Binds
    @Singleton
    fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindDeleteUserUseCase(useCase: DeleteUserUseCaseImpl): DeleteUserUseCase

    @Binds
    fun bindGetAllUsersOrderedByCreateAtUseCase(useCase: GetAllUsersOrderedByCreateAtUseCaseImpl): GetAllUsersOrderedByCreateAtUseCase

    @Binds
    fun bindGetLatestSelectedUserUseCase(useCase: GetLatestSelectedUserUseCaseImpl): GetLatestSelectedUserUseCase

    @Binds
    fun bindUpsertUserUseCase(useCase: UpsertUserUseCaseImpl): UpsertUserUseCase

    @Binds
    @Singleton
    fun bindTrainingRepository(repositoryImpl: TrainingRepositoryImpl): TrainingRepository

    @Binds
    fun bindDeleteTrainingUseCase(useCase: DeleteTrainingUseCaseImpl): DeleteTrainingUseCase

    @Binds
    fun bindGetAllTrainingsOrderedByStartAtUseCase(useCase: GetAllTrainingsOrderedByStartAtUseCaseImpl): GetAllTrainingsOrderedByStartAtUseCase

    @Binds
    fun bindGetTrainingByIdUseCase(useCase: GetTrainingByIdUseCaseImpl): GetTrainingByIdUseCase

    @Binds
    fun bindGetTrainingsOrderedByStartAtLimitedUseCase(useCase: GetTrainingsOrderedByStartAtLimitedUseCaseImpl): GetTrainingsOrderedByStartAtLimitedUseCase

    @Binds
    fun bindUpsertTrainingUseCase(useCase: UpsertTrainingUseCaseImpl): UpsertTrainingUseCase

    companion object {
        @Provides
        fun provideContext(app: Application): Context =
            app.applicationContext
    }
}