package com.example.runmate.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(date: Long): String {
    val sdf = SimpleDateFormat("MMMM dd HH:mm", Locale.getDefault())
    return sdf.format(Date(date))
}

fun formatTime(timeSeconds: Long): String {
    val seconds = timeSeconds % 60
    val minutes = timeSeconds / 60 % 60
    val hours = timeSeconds / 3600 % 24

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}
