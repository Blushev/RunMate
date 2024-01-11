package com.example.runmate.presenter.running

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentNowInRunningBinding
import com.example.runmate.di.ViewModelFactory
import com.example.runmate.di.appComponent
import com.example.runmate.presenter.main.MainViewModel
import com.example.runmate.util.requirePermission
import javax.inject.Inject

class NowInRunningFragment: Fragment(R.layout.fragment_now_in_running) {

    private val binding: FragmentNowInRunningBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var toMapListener: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showFailureMessage(false)
        showSuccess(true)

        binding.startTrainingButton.setOnClickListener {
            requireGpsPermission()
        }
    }

    private fun requireGpsPermission() {
        requirePermission(
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            successDelegate = {
                toMapListener?.invoke()
            },
            failureDelegate = {
                showFailureMessage(true)
            }
        )
    }

    private fun showSuccess(isShown: Boolean) {
        val visibility = if (isShown) View.VISIBLE else View.GONE
        binding.startTrainingButton.visibility = visibility
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