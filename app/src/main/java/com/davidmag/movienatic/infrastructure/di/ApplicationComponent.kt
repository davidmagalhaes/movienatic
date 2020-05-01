package com.davidmag.movienatic.infrastructure.di

import com.davidmag.movienatic.infrastructure.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    DatasourceModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

//        @BindsInstance
//        fun applicationBind(application: Application): Builder

    }

    fun inject(application : App)
}