package com.example.runmate.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(date: Long): String {
    val sdf = SimpleDateFormat("MMMM dd HH:mm", Locale.getDefault())
    return sdf.format(Date(date))
}
