package com.example.runmate.presenter.running

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentNowInRunningBinding
import com.example.runmate.di.appComponent
import com.example.runmate.services.TrackingService
import com.example.runmate.util.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.runmate.util.formatTime
import com.example.runmate.util.isServiceRunning
import com.example.runmate.util.requirePermission

class NowInRunningFragment: Fragment(R.layout.fragment_now_in_running) {

    private val binding: FragmentNowInRunningBinding by viewBinding()

    private var toMapListener: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showFailureMessage(false)
        showSuccess(true)

        requireGpsPermission()

        binding.startTrainingButton.setOnClickListener {
            startTraining()
            toMapListener?.invoke()
        }

        binding.nowInRunningContainer.setOnClickListener {
            toMapListener?.invoke()
        }

        TrackingService.currentTrainingData.observe(viewLifecycleOwner) { it1 ->
            it1?.let {
                binding.nowInRunningKal.text = it.calories.toString()
                binding.nowInRunningDistance.text = it.distance.toString()
            }
        }
        TrackingService.timerData.observe(viewLifecycleOwner) { it1 ->
            it1?.let {
                binding.nowInRunningTime.text = formatTime(it)
            }
        }
    }

    private fun startTraining() =
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = ACTION_START_OR_RESUME_SERVICE
            requireContext().startService(it)
        }

    private fun requireGpsPermission() {
        requirePermission(
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            successDelegate = {},
            failureDelegate = {
                showFailureMessage(true)
                showSuccess(false)
            }
        )
    }

    private fun showSuccess(isShown: Boolean) {
        val visibility = if (isShown) View.VISIBLE else View.GONE
        if (isServiceRunning(TrackingService::class.java)) {
            binding.nowInRunningContainer.visibility = visibility
        } else {
            binding.startTrainingButton.visibility = visibility
        }
    }

    private fun showFailureMessage(isShown: Boolean) {
        val visibility = if (isShown) View.VISIBLE else View.GONE
        binding.startTrainingFailureMessage.visibility = visibility
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(toMapListener: (() -> Unit)? = null) =
            NowInRunningFragment().apply {
                this.toMapListener = toMapListener
            }
    }
}