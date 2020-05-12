package com.onthecrow.trainy.di.activity

import com.onthecrow.trainy.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}