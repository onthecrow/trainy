package com.onthecrow.trainy.presentation.training

import androidx.fragment.app.viewModels
import com.onthecrow.trainy.R
import com.onthecrow.trainy.base.BaseBindingFragment
import com.onthecrow.trainy.databinding.FragmentFeedBinding

class TrainingFragment : BaseBindingFragment<TrainingViewModel, FragmentFeedBinding>() {

    override val layoutRes = R.layout.fragment_training

    override val viewModel: TrainingViewModel by viewModels { viewModelFactory }
}