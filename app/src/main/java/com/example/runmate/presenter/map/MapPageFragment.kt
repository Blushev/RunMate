package com.example.runmate.presenter.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentMapPageBinding

class MapPageFragment: Fragment(R.layout.fragment_map_page) {
    private val binding: FragmentMapPageBinding by viewBinding()

    private var backToHomeListener: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mapPageButtonBack.setOnClickListener {
            backToHomeListener?.invoke()
            true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(backToHomeListener: (() -> Unit)) =
            MapPageFragment().apply {
                this.backToHomeListener = backToHomeListener
            }
    }
}