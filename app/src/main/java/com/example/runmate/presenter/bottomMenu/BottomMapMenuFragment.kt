package com.example.runmate.presenter.bottomMenu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentBottomMapMenuBinding
import com.example.runmate.di.ViewModelFactory
import com.example.runmate.di.appComponent
import com.example.runmate.presenter.main.MainViewModel
import com.example.runmate.presenter.map.TrainingViewModel
import com.example.runmate.services.TrackingService
import com.example.runmate.util.Constants.ACTION_STOP_SERVICE
import com.example.runmate.util.formatTime
import javax.inject.Inject

class BottomMapMenuFragment: Fragment(R.layout.fragment_bottom_map_menu) {

    private val binding: FragmentBottomMapMenuBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels() { viewModelFactory }
    private val trainingViewModel: TrainingViewModel by viewModels() { viewModelFactory }

    private var backToHomeListener: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TrackingService.currentTrainingData.observe(viewLifecycleOwner) { it1 ->
            it1?.let {
                binding.bottomMapMenuCalorieValue.text = it.calories.toString()
                binding.bottomMapMenuDestinationValue.text = it.distance.toString()
                binding.bottomMapMenuSpeedValue.text = it.averageSpeed.toString()
                binding.bottomMapMenuButtonStop.setOnClickListener {
                    mainViewModel.liveCurrentUserData.observe(viewLifecycleOwner) { it2 ->
                        it2?.let {
                            stopTraining(it2.id)
                            backToHomeListener?.invoke()
                        }
                    }
                }
            }
        }
        TrackingService.timerData.observe(viewLifecycleOwner) { it1 ->
            it1?.let {
                binding.bottomMapMenuSubtitleTime.text = formatTime(it)
            }
        }
    }

    private fun stopTraining(userId: Int) {
        TrackingService.currentTrainingData.value?.let { it2 ->
            trainingViewModel.upsertTraining(
                it2, userId)
        }
        Intent(requireContext(), TrackingService::class.java).also { it3 ->
            it3.action = ACTION_STOP_SERVICE
            requireContext().stopService(it3)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(backToHomeListener: (() -> Unit)) =
            BottomMapMenuFragment().apply {
                this.backToHomeListener = backToHomeListener
            }
    }
}