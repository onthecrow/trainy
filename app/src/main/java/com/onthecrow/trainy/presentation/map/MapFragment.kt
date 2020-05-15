package com.onthecrow.trainy.presentation.map

import androidx.fragment.app.viewModels
import com.onthecrow.trainy.R
import com.onthecrow.trainy.base.BaseBindingFragment
import com.onthecrow.trainy.databinding.FragmentFeedBinding

class MapFragment : BaseBindingFragment<MapViewModel, FragmentFeedBinding>() {

    override val layoutRes = R.layout.fragment_map

    override val viewModel: MapViewModel by viewModels { viewModelFactory }
}