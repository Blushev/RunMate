package com.example.runmate.presenter.bottomMenu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentBottomMapMenuBinding
import com.example.runmate.services.TrackingService
import com.example.runmate.util.Constants.ACTION_STOP_SERVICE
import com.example.runmate.util.formatTime

class BottomMapMenuFragment: Fragment(R.layout.fragment_bottom_map_menu) {

    private val binding: FragmentBottomMapMenuBinding by viewBinding()
    private var backToHomeListener: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TrackingService.currentTrainingData.observe(viewLifecycleOwner) { it1 ->
            it1?.let {
                binding.bottomMapMenuCalorieValue.text = it.calories.toString()
                binding.bottomMapMenuDestinationValue.text = it.distance.toString()
                binding.bottomMapMenuSpeedValue.text = it.averageSpeed.toString()
                binding.bottomMapMenuButtonStop.setOnClickListener {
                    stopTraining()
                    backToHomeListener?.invoke()
                }
            }
        }
        TrackingService.timerData.observe(viewLifecycleOwner) { it1 ->
            it1?.let {
                binding.bottomMapMenuSubtitleTime.text = formatTime(it)
            }
        }
    }

    private fun stopTraining() =
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = ACTION_STOP_SERVICE
            requireContext().stopService(it)
        }

    companion object {
        @JvmStatic
        fun newInstance(backToHomeListener: (() -> Unit)) =
            BottomMapMenuFragment().apply {
                this.backToHomeListener = backToHomeListener
            }
    }
}