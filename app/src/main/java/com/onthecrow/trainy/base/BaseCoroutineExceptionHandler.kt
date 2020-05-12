package com.onthecrow.trainy.base

import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class BaseCoroutineExceptionHandler : CoroutineExceptionHandler {

    override val key: CoroutineContext.Key<*>
        get() = CoroutineExceptionHandler.Key

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Timber.e(exception)
    }
}