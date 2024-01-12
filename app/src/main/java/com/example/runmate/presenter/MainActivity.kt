package com.example.runmate.presenter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.ActivityMainBinding
import com.example.runmate.presenter.main.MainFragment
import com.example.runmate.util.Constants.ACTION_SHOW_TRACKING_FRAGMENT


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentRootLayout.id, MainFragment.newInstance())
            .commit()
    }
}