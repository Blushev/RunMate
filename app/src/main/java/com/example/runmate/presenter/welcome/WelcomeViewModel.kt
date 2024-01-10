package com.example.runmate.presenter.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runmate.data.db.model.UserLevel
import com.example.runmate.data.model.UserModel
import com.example.runmate.domain.user.UpsertUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val upsertUserUseCase: UpsertUserUseCase
): ViewModel() {

    private var user = UserModel(
        firstName = "Username",
        level = UserLevel.Beginner,
        createAt = System.currentTimeMillis(),
        updateAt = System.currentTimeMillis(),
        isDeleted = false,
        isSelected = true
    )

    fun validateForm(firstName: String): Boolean {
        if (firstName.trim().isEmpty())
            return false
        user = user.copy(
            firstName = firstName.trim()
        )

        return true
    }

    fun createUser() {
        viewModelScope.launch {
            upsertUserUseCase(user)
        }
    }
}