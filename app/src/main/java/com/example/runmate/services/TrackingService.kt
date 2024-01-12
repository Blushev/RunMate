package com.example.runmate.services

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.runmate.R
import com.example.runmate.data.model.TrainingModel
import com.example.runmate.presenter.MainActivity
import com.example.runmate.util.Constants.ACTION_PAUSE_SERVICE
import com.example.runmate.util.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.example.runmate.util.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.runmate.util.Constants.ACTION_STOP_SERVICE
import com.example.runmate.util.Constants.NOTIFICATION_CHANNEL_ID
import com.example.runmate.util.Constants.NOTIFICATION_CHANNEL_NAME
import com.example.runmate.util.Constants.NOTIFICATION_ID
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt

class TrackingService: LifecycleService() {

    var isFirstRun = true

    companion object {
        private val _currentTrainingData = MutableLiveData<TrainingModel>()
        val currentTrainingData: LiveData<TrainingModel>
            get() = _currentTrainingData
        private val _pathPoints = MutableLiveData<MutableList<LatLng>>()
        val pathPoints: LiveData<MutableList<LatLng>>
            get() = _pathPoints
        private val _timerData = MutableLiveData<Long>()
        val timerData: LiveData<Long>
            get() = _timerData
    }

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private fun postInitialValues() {
        _currentTrainingData.postValue(TrainingModel(
            startAt = System.currentTimeMillis(),
            distance = 0f,
            calories = 0f,
            averageSpeed = 0f
        ))
        _pathPoints.postValue(mutableListOf())
        _timerData.postValue(0)
    }

    override fun onCreate() {
        super.onCreate()
        postInitialValues()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        updateLocationTracking()
    }

    private val timerHandler = Handler()
    private val timerRunnable = object : Runnable {
        override fun run() {
            _timerData.value?.let {
                val newValue = System.currentTimeMillis()
                _timerData.postValue(newValue)
            }
            timerHandler.postDelayed(this, 1000L)
        }
    }

    private fun startTimer() {
        timerHandler.postDelayed(timerRunnable, 1000L)
    }

    private fun stopTimer() {
        timerHandler.removeCallbacks(timerRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            Log.i("qwerty", "on load location result")
            super.onLocationResult(result)
            result.locations.let { locations ->
                for (location in locations) {
                    addTrainingData(location, location.speed)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun updateLocationTracking() {
        Log.i("qwerty", "update location tracing")

        val locationClient: LocationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager

        locationClient.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0L,
            0f,
            locationListener
        )

//        val request = LocationRequest().apply {
//            interval = LOCATION_UPDATE_INTERVAL
//            fastestInterval = FASTEST_LOCATION_INTERVAL
//            priority = Priority.PRIORITY_HIGH_ACCURACY
//        }
//        fusedLocationProviderClient.requestLocationUpdates(
//            request,
//            locationCallback,
//            Looper.getMainLooper()
//        )
    }

    private val locationListener = LocationListener { location ->
        addTrainingData(location, location.speed)
    }

    private fun addTrainingData(location: Location?, speed: Float) {
        location?.let {
            Log.i("qwerty", location.longitude.toString())
            val pos = LatLng(location.latitude, location.longitude)
            pathPoints.value?.apply {
                val prevPos = last()
                add(pos)
                _pathPoints.postValue(this)
                val distance = calculateDistance(prevPos.latitude, prevPos.longitude, pos.latitude, pos.longitude).toFloat()
                currentTrainingData.value?.let { it1 ->
                    val training = currentTrainingData.value?.copy(
                        distance = it1.distance + distance,
                        averageSpeed = (it1.averageSpeed + speed) / 2,
                        calories = it1.calories + calculateCaloriesBurned(speed, distance)
                    )
                    training?.let { it2 ->
                        _currentTrainingData.postValue(it2)
                    }
                }
            }
        }
    }

    private fun calculateCaloriesBurned(speed: Float, distance: Float): Float {
        val caloriesPerKm: Float = when (speed) {
            in 0.0..10.0 -> 60f
            in 10.0..14.0 -> 70f
            else -> 80f
        }

        val totalCalories = caloriesPerKm * distance

        return round(totalCalories * 100) / 100
    }

    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadius = 6371

        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return earthRadius * c
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    startTimer()
                    if (isFirstRun) {
                        startForegroundService()
                        isFirstRun = false
                    }
                }
                ACTION_PAUSE_SERVICE -> {

                }
                ACTION_STOP_SERVICE -> {
                    super.stopForeground(true)
                    super.stopSelf()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.baseline_directions_run_24)
            .setContentTitle(NOTIFICATION_CHANNEL_NAME)
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingIntent())

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this, MainActivity::class.java).also {
            it.action = ACTION_SHOW_TRACKING_FRAGMENT
        },
        FLAG_UPDATE_CURRENT
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }
}