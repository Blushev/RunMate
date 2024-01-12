package com.example.runmate.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(date: Long): String {
    val sdf = SimpleDateFormat("MMMM dd HH:mm", Locale.getDefault())
    return sdf.format(Date(date))
}

fun formatTime(time: Long): String {
    val seconds = time / 1000 % 60
    val minutes = time / 60000 % 60
    val hours = time / 3600000 % 24

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}
