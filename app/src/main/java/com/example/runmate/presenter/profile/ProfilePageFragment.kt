package com.example.runmate.presenter.profile

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.runmate.R
import com.example.runmate.databinding.FragmentProfilePageBinding

class ProfilePageFragment : Fragment(R.layout.fragment_profile_page) {
    private val binding: FragmentProfilePageBinding by viewBinding()

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfilePageFragment().apply {

            }
    }
}