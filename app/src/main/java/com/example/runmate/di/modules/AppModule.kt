package com.example.runmate.di.modules

import dagger.Module

@Module(
    includes = [
        AppBindsModule::class,
        ViewModelModule::class,
//        NetworkModule::class,
        DatabaseModule::class
    ]
)
class AppModule