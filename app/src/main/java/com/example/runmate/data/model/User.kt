package com.example.runmate.data.model

data class User(
    val userId: String,
    val name: String,
    val photo: String,
    val statistics: UserStatistics,
    val profileSettings: UserProfileSettings

)