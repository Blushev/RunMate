package com.example.runmate.presenter.progressWeek

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentProgressWeekBinding
import com.example.runmate.di.ViewModelFactory
import com.example.runmate.di.appComponent
import com.example.runmate.presenter.main.MainViewModel
import javax.inject.Inject

class ProgressWeekFragment: Fragment(R.layout.fragment_progress_week) {

    private val binding: FragmentProgressWeekBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }
    private val progressWeekViewModel: ProgressWeekViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.liveCurrentUserData.observe(viewLifecycleOwner) {
            progressWeekViewModel.getTraveledDistance(it.id)
        }

        mainViewModel.liveCurrentUserData.observe(viewLifecycleOwner) { it1 ->
            if (it1 != null) {
                binding.progressWeekGoal.text = "${it1.goal} km"

                progressWeekViewModel.liveTraveledDistanceData.observe(viewLifecycleOwner) { it3 ->
                    binding.progressWeekTraveledDistance.text = "$it3 km"
                    binding.progressWeekRemainingDistance.text = "${(it1.goal - it3).coerceAtLeast(0f)} km"
                    binding.progressWeekProgressBar.progress = it3.toInt()
                    binding.progressWeekProgressBar.max = it1.goal.toInt()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }
}