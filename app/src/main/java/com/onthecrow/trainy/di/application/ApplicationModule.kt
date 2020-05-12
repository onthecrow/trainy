package com.onthecrow.trainy.di.application

import android.content.Context
import com.onthecrow.trainy.base.BaseCoroutineExceptionHandler
import com.onthecrow.trainy.binding.FragmentDataBindingComponent
import com.onthecrow.trainy.data.storage.KeyValueStorage
import com.onthecrow.trainy.di.activity.ActivityBindingModule
import com.onthecrow.trainy.di.fragment.FragmentBindingModule
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineExceptionHandler

@Module(
    includes = [ActivityBindingModule::class, FragmentBindingModule::class]
)
class ApplicationModule {

    @Provides
    fun provideKeyValueStorage(context: Context) = KeyValueStorage(context)

    @Provides
    fun provideDataBindingComponent(context: Context) = FragmentDataBindingComponent(context)

    @Provides
    fun provideCoroutineExceptionHandler(): CoroutineExceptionHandler =
        BaseCoroutineExceptionHandler()
}