package com.example.runmate.presenter.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.data.db.model.UserLevel
import com.example.runmate.data.model.UserModel
import com.example.runmate.databinding.FragmentProfilePageBinding
import com.example.runmate.di.ViewModelFactory
import com.example.runmate.di.appComponent
import com.example.runmate.presenter.main.MainViewModel
import javax.inject.Inject

class ProfilePageFragment : Fragment(R.layout.fragment_profile_page) {
    private val binding: FragmentProfilePageBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels() { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.liveCurrentUserData.observe(viewLifecycleOwner) {
            initUserPanel(it)
        }
    }

    private fun initUserPanel(currentUser: UserModel?) {
        if (currentUser == null) {
            binding.profilePageProfilePageProfilePanel.profilePanelName.text = "<username>"
            binding.profilePageProfilePageProfilePanel.profilePanelLevel.text = UserLevel.Beginner.toString()
//            binding.profilePageProfilePageProfilePanel.profilePanelImage.sourceLayoutResId
        } else {
            binding.profilePageProfilePageProfilePanel.profilePanelName.text = currentUser.firstName
            binding.profilePageProfilePageProfilePanel.profilePanelLevel.text = currentUser.level.toString()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfilePageFragment().apply {

            }
    }
}