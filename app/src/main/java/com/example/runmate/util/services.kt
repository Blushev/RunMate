package com.example.runmate.util

import android.app.ActivityManager
import android.content.Context
import androidx.fragment.app.Fragment

fun Fragment.isServiceRunning(serviceClass: Class<*>): Boolean {
    val activityManager = requireContext().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}
