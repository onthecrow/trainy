package com.onthecrow.trainy

import androidx.databinding.DataBindingUtil
import com.facebook.appevents.AppEventsLogger
import com.onthecrow.trainy.binding.FragmentDataBindingComponent
import com.onthecrow.trainy.di.application.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class TrainyApp : DaggerApplication() {

    @Inject
    lateinit var dataBindingComponent: FragmentDataBindingComponent

    override fun onCreate() {
        super.onCreate()
        AppEventsLogger.activateApp(this)
        Timber.plant(Timber.DebugTree())
        DataBindingUtil.setDefaultComponent(dataBindingComponent)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.factory().create(applicationContext).also {
            it.inject(this)
        }
}