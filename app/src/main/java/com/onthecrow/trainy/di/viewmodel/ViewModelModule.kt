package com.onthecrow.trainy.di.viewmodel

import androidx.lifecycle.ViewModel
import com.onthecrow.trainy.presentation.auth.AuthViewModel
import com.onthecrow.trainy.presentation.crop.ImageCropViewModel
import com.onthecrow.trainy.presentation.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(): ViewModel = MainViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(ImageCropViewModel::class)
    fun provideImageCropViewModel(): ViewModel = ImageCropViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun provideAuthViewModel(): ViewModel = AuthViewModel()
}