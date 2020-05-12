package com.onthecrow.trainy.di.application

import android.content.Context
import com.onthecrow.trainy.TrainyApp
import com.onthecrow.trainy.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent : AndroidInjector<TrainyApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    override fun inject(trainyApp: TrainyApp)
}