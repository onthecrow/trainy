package com.onthecrow.trainy.presentation.chat

import androidx.fragment.app.viewModels
import com.onthecrow.trainy.R
import com.onthecrow.trainy.base.BaseBindingFragment
import com.onthecrow.trainy.databinding.FragmentFeedBinding

class ChatFragment : BaseBindingFragment<ChatViewModel, FragmentFeedBinding>() {

    override val layoutRes = R.layout.fragment_chat

    override val viewModel: ChatViewModel by viewModels { viewModelFactory }
}