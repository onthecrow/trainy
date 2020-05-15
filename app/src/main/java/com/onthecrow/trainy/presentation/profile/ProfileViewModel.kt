package com.onthecrow.trainy.presentation.profile

import androidx.lifecycle.MutableLiveData
import com.onthecrow.trainy.base.BaseViewModel
import com.onthecrow.trainy.common.Event

class ProfileViewModel : BaseViewModel() {
    val onSettingsClick = MutableLiveData<Event<Any>>()
}