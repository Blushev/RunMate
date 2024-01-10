package com.example.runmate.di

import android.content.Context
import com.example.runmate.RunMateApplication

val Context.appComponent: AppComponent
    get() = when(this) {
        is RunMateApplication -> appComponent
        else -> applicationContext.appComponent
    }