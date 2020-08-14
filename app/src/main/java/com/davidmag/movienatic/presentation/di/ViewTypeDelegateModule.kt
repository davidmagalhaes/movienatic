package com.davidmag.movienatic.presentation.di

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
class ViewTypeDelegateModule {
    @Provides
    @IntoMap
    @IntKey(10)
    fun providesConnectionErrorViewType() {

    }
}