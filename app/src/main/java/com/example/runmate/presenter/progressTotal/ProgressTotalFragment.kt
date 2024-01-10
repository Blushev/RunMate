package com.example.runmate.presenter.progressTotal

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentProgressTotalBinding
import com.example.runmate.di.ViewModelFactory
import com.example.runmate.di.appComponent
import com.example.runmate.presenter.main.MainViewModel
import javax.inject.Inject

class ProgressTotalFragment: Fragment(R.layout.fragment_progress_total) {

    private val binding: FragmentProgressTotalBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }
    private val progressTotalViewModel: ProgressTotalViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.liveCurrentUserData.observe(viewLifecycleOwner) { it1 ->
            if (it1 != null) {
                progressTotalViewModel.liveDistanceTraveledData.observe(viewLifecycleOwner) {
                    binding.progressTotalKmValue.text = it.toString()
                }
                progressTotalViewModel.liveTimeTraveledData.observe(viewLifecycleOwner) {
                    binding.progressTotalHValue.text = it.toString()
                }
                progressTotalViewModel.liveCaloriesTraveledData.observe(viewLifecycleOwner) {
                    binding.progressTotalCValue.text = it.toString()
                }

                progressTotalViewModel.getLiveData(it1.id)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }
}