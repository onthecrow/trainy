package com.onthecrow.trainy.presentation.auth

import androidx.lifecycle.MutableLiveData
import com.onthecrow.trainy.base.BaseViewModel
import com.onthecrow.trainy.common.Event

class AuthViewModel : BaseViewModel() {

    val phoneNumber = MutableLiveData<String>()
    val code = MutableLiveData<String>()

    val onPhoneAuthClick = MutableLiveData<Event<Any>>()
    val onGoogleAuthClick = MutableLiveData<Event<Any>>()
    val onFacebookAuthClick = MutableLiveData<Event<Any>>()
    val onVKAuthClick = MutableLiveData<Event<Any>>()

}