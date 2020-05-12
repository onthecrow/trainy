package com.onthecrow.trainy.binding

import android.content.Context
import androidx.databinding.DataBindingComponent

class FragmentDataBindingComponent(
    applicationContext: Context
) : DataBindingComponent {

    private val adapters = FragmentBindingAdapters(applicationContext)

    override fun getFragmentBindingAdapters() = adapters
}
