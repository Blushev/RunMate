package com.example.runmate.presenter.main

import com.example.runmate.R

enum class BottomMenuState(val navigationId: Int) {
    Home(R.id.navigation_home),
    Profile(R.id.navigation_profile);

    companion object {
        fun fromId(id: Int): BottomMenuState? = values().find { it.navigationId == id }
    }
}