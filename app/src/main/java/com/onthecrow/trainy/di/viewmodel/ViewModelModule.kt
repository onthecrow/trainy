package com.onthecrow.trainy.di.viewmodel

import androidx.lifecycle.ViewModel
import com.onthecrow.trainy.presentation.auth.AuthViewModel
import com.onthecrow.trainy.presentation.chat.ChatViewModel
import com.onthecrow.trainy.presentation.crop.ImageCropViewModel
import com.onthecrow.trainy.presentation.feed.FeedViewModel
import com.onthecrow.trainy.presentation.main.MainViewModel
import com.onthecrow.trainy.presentation.map.MapViewModel
import com.onthecrow.trainy.presentation.profile.ProfileViewModel
import com.onthecrow.trainy.presentation.settings.SettingsViewModel
import com.onthecrow.trainy.presentation.training.TrainingViewModel
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

    @Provides
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    fun provideFeedViewModel(): ViewModel = FeedViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    fun provideMapViewModel(): ViewModel = MapViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    fun provideChatViewModel(): ViewModel = ChatViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(TrainingViewModel::class)
    fun provideTrainingViewModel(): ViewModel = TrainingViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun provideSettingsViewModel(): ViewModel = SettingsViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun provideProfileViewModel(): ViewModel = ProfileViewModel()
}