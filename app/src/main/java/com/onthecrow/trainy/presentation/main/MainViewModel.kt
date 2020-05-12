package com.onthecrow.trainy.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.onthecrow.trainy.base.BaseViewModel
import com.onthecrow.trainy.common.Event

class MainViewModel : BaseViewModel() {

    private val mutableIsLoggedIn = MutableLiveData<Event<Any>>()

    val isLoggedIn: LiveData<Event<Any>> get() = mutableIsLoggedIn

    init {
        if (FirebaseAuth.getInstance().currentUser != null) {
            mutableIsLoggedIn.postValue(Event(Any()))
        }
    }
}