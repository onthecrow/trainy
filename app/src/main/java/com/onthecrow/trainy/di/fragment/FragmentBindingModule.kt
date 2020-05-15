package com.onthecrow.trainy.di.fragment

import com.onthecrow.trainy.presentation.auth.AuthFragment
import com.onthecrow.trainy.presentation.chat.ChatFragment
import com.onthecrow.trainy.presentation.crop.ImageCropFragment
import com.onthecrow.trainy.presentation.feed.FeedFragment
import com.onthecrow.trainy.presentation.main.MainFragment
import com.onthecrow.trainy.presentation.map.MapFragment
import com.onthecrow.trainy.presentation.profile.ProfileFragment
import com.onthecrow.trainy.presentation.settings.SettingsFragment
import com.onthecrow.trainy.presentation.training.TrainingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindImageCropFragment(): ImageCropFragment

    @ContributesAndroidInjector
    abstract fun bindAuthFragment(): AuthFragment

    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun bingFeedFragment(): FeedFragment

    @ContributesAndroidInjector
    abstract fun bingMapFragment(): MapFragment

    @ContributesAndroidInjector
    abstract fun bingChatFragment(): ChatFragment

    @ContributesAndroidInjector
    abstract fun bingTrainingFragment(): TrainingFragment

    @ContributesAndroidInjector
    abstract fun bindSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun bindProfileFragment(): ProfileFragment
}