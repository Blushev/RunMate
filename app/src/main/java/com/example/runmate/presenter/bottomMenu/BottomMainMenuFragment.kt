package com.example.runmate.presenter.bottomMenu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentBottomMainMenuBinding

class BottomMainMenuFragment: Fragment(R.layout.fragment_bottom_main_menu) {
    private val binding: FragmentBottomMainMenuBinding by viewBinding()

    private var setOnItemSelectedListener: ((Int) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomMainMenuNavigation.setOnItemSelectedListener {
            setOnItemSelectedListener?.invoke(it.itemId)
            true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(setOnItemSelectedListener: ((Int) -> Unit)) =
            BottomMainMenuFragment().apply {
                this.setOnItemSelectedListener = setOnItemSelectedListener
            }
    }
}