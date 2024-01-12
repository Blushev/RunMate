package com.example.runmate.presenter.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runmate.R
import com.example.runmate.databinding.FragmentMainBinding
import com.example.runmate.di.ViewModelFactory
import com.example.runmate.di.appComponent
import com.example.runmate.presenter.bottomMenu.BottomMainMenuFragment
import com.example.runmate.presenter.bottomMenu.BottomMapMenuFragment
import com.example.runmate.presenter.home.HomePageFragment
import com.example.runmate.presenter.map.MapPageFragment
import com.example.runmate.presenter.profile.ProfilePageFragment
import com.example.runmate.presenter.welcome.WelcomePageFragment
import javax.inject.Inject

class MainFragment: Fragment(R.layout.fragment_main) {
    private var binding: FragmentMainBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels() { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.liveCurrentUserData.observe(viewLifecycleOwner) {
            if (it != null) {
                setBottomMenu(R.layout.fragment_bottom_main_menu)
                setMainFragmentContainerView(R.id.welcomePageFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun setBottomMenu(bottomMenuFragmentId: Int) {
        val bottomMenuHostId = binding?.bottomMenuHost?.id
        bottomMenuHostId ?: return
        val transaction = childFragmentManager.beginTransaction()
        when (bottomMenuFragmentId) {
            R.layout.fragment_bottom_main_menu ->
                transaction.replace(bottomMenuHostId,
                     BottomMainMenuFragment.newInstance { mainMenuId -> setMainFragmentContainerView(mainMenuId) })
            R.layout.fragment_bottom_map_menu ->
                transaction.replace(bottomMenuHostId,
                    BottomMapMenuFragment.newInstance {
                        setBottomMenu(R.layout.fragment_bottom_main_menu)
                        setMainFragmentContainerView(R.id.navigation_home)
                    })
        }
        transaction.commit()
    }

    private fun setMainFragmentContainerView(@IdRes mainFragmentId: Int) {
        val mainFragmentContainerViewId = binding?.mainFragmentContainerView?.id
        mainFragmentContainerViewId ?: return

        val transaction = childFragmentManager.beginTransaction()
        when (mainFragmentId) {
            R.id.navigation_home ->
                transaction.replace(mainFragmentContainerViewId, HomePageFragment.newInstance {
                    setBottomMenu(R.layout.fragment_bottom_map_menu)
                    setMainFragmentContainerView(R.id.mapPageFragment)
                })
            R.id.navigation_profile ->
                transaction.replace(mainFragmentContainerViewId, ProfilePageFragment.newInstance())
            R.id.welcomePageFragment ->
                transaction.replace(mainFragmentContainerViewId, WelcomePageFragment.newInstance {
                    setMainFragmentContainerView(R.id.navigation_home)
                })
            R.id.mapPageFragment ->
                transaction.replace(mainFragmentContainerViewId, MapPageFragment.newInstance {
                    setBottomMenu(R.layout.fragment_bottom_main_menu)
                    setMainFragmentContainerView(R.id.navigation_home)
                })
        }
        transaction.commit()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {

            }
    }
}