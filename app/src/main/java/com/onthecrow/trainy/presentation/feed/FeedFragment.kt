package com.onthecrow.trainy.presentation.feed

import androidx.fragment.app.viewModels
import com.onthecrow.trainy.R
import com.onthecrow.trainy.base.BaseBindingFragment
import com.onthecrow.trainy.databinding.FragmentFeedBinding

class FeedFragment : BaseBindingFragment<FeedViewModel, FragmentFeedBinding>() {

    override val layoutRes = R.layout.fragment_feed

    override val viewModel: FeedViewModel by viewModels { viewModelFactory }
}