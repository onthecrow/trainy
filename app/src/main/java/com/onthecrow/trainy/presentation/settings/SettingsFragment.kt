package com.onthecrow.trainy.presentation.settings

import androidx.fragment.app.viewModels
import com.onthecrow.trainy.R
import com.onthecrow.trainy.base.BaseBindingFragment
import com.onthecrow.trainy.databinding.FragmentSettingsBinding

class SettingsFragment : BaseBindingFragment<SettingsViewModel, FragmentSettingsBinding>() {

    override val layoutRes = R.layout.fragment_settings

    override val viewModel: SettingsViewModel by viewModels { viewModelFactory }
}