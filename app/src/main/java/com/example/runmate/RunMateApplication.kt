package com.example.runmate

import android.app.Application
import com.example.runmate.di.AppComponent
import com.example.runmate.di.DaggerAppComponent

class RunMateApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder().application(this).build()
        super.onCreate()
    }
}