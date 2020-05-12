package com.onthecrow.trainy.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val mutableIsLoading = MutableLiveData(false)

    @Suppress("MemberVisibilityCanBePrivate")
    protected val mutableError = MutableLiveData<Throwable>()
    protected val mutableErrorText = MutableLiveData<String>()

    val isLoading: LiveData<Boolean> get() = mutableIsLoading
    val error: LiveData<Throwable> get() = mutableError
    val errorText: LiveData<String> get() = mutableErrorText
}