package com.onthecrow.trainy.base

import android.os.Bundle
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

open class BaseDialogFragment<T : BaseViewModel> : DaggerDialogFragment() {

    @Inject
    protected lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}