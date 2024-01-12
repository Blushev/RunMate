package com.example.runmate.presenter.welcome

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentWelcomePageBinding
import com.example.runmate.di.ViewModelFactory
import com.example.runmate.di.appComponent
import com.example.runmate.presenter.main.MainViewModel
import com.example.runmate.util.requirePermission
import javax.inject.Inject

class WelcomePageFragment: Fragment(R.layout.fragment_welcome_page) {
    private val binding: FragmentWelcomePageBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val welcomeViewModel: WelcomeViewModel by viewModels() { viewModelFactory }
    private val mainViewModel: MainViewModel by viewModels() { viewModelFactory }

    private var toHomeListener: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.liveCurrentUserData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.welcomePageButton.visibility = View.GONE
                toHomeListener?.invoke()
//                val direction = WelcomePageFragmentDirections.actionWelcomePageFragmentToHomePageFragment()
//                findNavController().navigate(direction)
            }
        }

        binding.welcomePageUsernameInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                welcomeViewModel.validateForm(s.toString())
            }
        })

        binding.welcomePageButton.setOnClickListener {
            welcomeViewModel.createUser()
            toHomeListener?.invoke()
//            val direction = WelcomePageFragmentDirections.actionWelcomePageFragmentToHomePageFragment()
//            findNavController().navigate(direction)
            true
        }

        showFailureMessage(false)
//        requireGpsPermission()
    }

    private fun requireGpsPermission() {
        requirePermission(
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            successDelegate = {},
            failureDelegate = {
                showFailureMessage(true)
            }
        )
    }

    private fun showFailureMessage(isShown: Boolean) {
        val visibility = if (isShown) View.VISIBLE else View.GONE
        binding.welcomePageFailureMessage.visibility = visibility
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(toHomeListener: (() -> Unit)? = null) =
            WelcomePageFragment().apply {
                this.toHomeListener = toHomeListener
            }
    }
}