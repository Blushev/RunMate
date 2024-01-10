package com.example.runmate.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runmate.data.model.UserModel
import com.example.runmate.domain.user.GetLatestSelectedUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getLatestSelectedUserUseCase: GetLatestSelectedUserUseCase,
): ViewModel() {

    private val _liveCurrentUserData = MutableLiveData<UserModel>()
    val liveCurrentUserData: LiveData<UserModel>
        get() {
            return _liveCurrentUserData
        }

    init {
        viewModelScope.launch {
            getLatestSelectedUserUseCase().collect {
                if (it.isNotEmpty()) {
                    _liveCurrentUserData.postValue(it[0])
                }
            }
        }
    }
}