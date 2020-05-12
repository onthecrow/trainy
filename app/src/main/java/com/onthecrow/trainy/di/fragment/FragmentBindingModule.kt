package com.onthecrow.trainy.di.fragment

import com.onthecrow.trainy.presentation.auth.AuthFragment
import com.onthecrow.trainy.presentation.crop.ImageCropFragment
import com.onthecrow.trainy.presentation.main.MainFragment
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
}