package com.onthecrow.trainy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingFragment<T : BaseViewModel, E : ViewDataBinding> : BaseFragment<T>() {

    protected lateinit var binding: E

    @get:LayoutRes
    abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = if (!::binding.isInitialized) {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.root
    } else {
        binding.root
    }
}