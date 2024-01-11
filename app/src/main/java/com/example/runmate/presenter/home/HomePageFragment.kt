package com.example.runmate.presenter.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.data.db.model.UserLevel
import com.example.runmate.data.model.UserModel
import com.example.runmate.databinding.FragmentHomePageBinding
import com.example.runmate.di.ViewModelFactory
import com.example.runmate.di.appComponent
import com.example.runmate.presenter.eventList.EventListFragment
import com.example.runmate.presenter.main.MainViewModel
import com.example.runmate.presenter.running.NowInRunningFragment
import javax.inject.Inject

class HomePageFragment : Fragment(R.layout.fragment_home_page) {
    private val binding: FragmentHomePageBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels() { viewModelFactory }

    private var toMapListener: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.liveCurrentUserData.observe(viewLifecycleOwner) {
            initUserPanel(it)
        }

        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(binding.homePageNowInRunning.id, NowInRunningFragment.newInstance(toMapListener))
        transaction.replace(binding.homePageEventList.id, EventListFragment.newInstance(3))
        transaction.commit()
    }

    private fun initUserPanel(currentUser: UserModel?) {
        if (currentUser == null) {
            binding.homePageProfilePageProfilePanel.profilePanelName.text = "<username>"
            binding.homePageProfilePageProfilePanel.profilePanelLevel.text = UserLevel.Beginner.toString()
//            binding.homePageProfilePageProfilePanel.profilePanelImage.sourceLayoutResId
        } else {
            binding.homePageProfilePageProfilePanel.profilePanelName.text = currentUser.firstName
            binding.homePageProfilePageProfilePanel.profilePanelLevel.text = currentUser.level.toString()
//            binding.homePageProfilePageProfilePanel.profilePanelImage.sourceLayoutResId
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(toMapListener: (() -> Unit)? = null) =
            HomePageFragment().apply {
                this.toMapListener = toMapListener
            }
    }
}