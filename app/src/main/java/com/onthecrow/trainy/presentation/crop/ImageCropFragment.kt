package com.onthecrow.trainy.presentation.crop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.onthecrow.trainy.base.BaseFragment
import com.onthecrow.trainy.R
import com.onthecrow.trainy.databinding.FragmentImageCropBinding

class ImageCropFragment : BaseFragment<ImageCropViewModel>() {

    override val viewModel: ImageCropViewModel by activityViewModels { viewModelFactory }

    private lateinit var binding: FragmentImageCropBinding

//    private val args: ImageCropFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = if (!::binding.isInitialized) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_crop, container, false)
        binding.root
    } else {
        binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.setRawImage(args.uri)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.onCancelClick.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { findNavController().navigateUp() }
        })
    }
}