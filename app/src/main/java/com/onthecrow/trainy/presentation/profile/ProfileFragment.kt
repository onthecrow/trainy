package com.onthecrow.trainy.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.onthecrow.trainy.R
import com.onthecrow.trainy.base.BaseBindingFragment
import com.onthecrow.trainy.databinding.FragmentProfileBinding

class ProfileFragment : BaseBindingFragment<ProfileViewModel, FragmentProfileBinding>() {

    override val layoutRes = R.layout.fragment_profile

    override val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.onSettingsClick.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSettingsFragment())
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }
}