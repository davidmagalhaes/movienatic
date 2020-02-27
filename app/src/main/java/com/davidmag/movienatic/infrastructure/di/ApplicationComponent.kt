package com.davidmag.movienatic.infrastructure.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder

    }

    fun inject(application : Application)
}