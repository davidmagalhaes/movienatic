package com.davidmag.movienatic.infrastructure.di

import android.app.Application
import com.davidmag.movienatic.domain.usecase.*
import com.davidmag.movienatic.infrastructure.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    PersistenceModule::class,
    NetworkModule::class,
    DatasourceModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder
    }

    fun inject(application : App)

    fun fetchMoviesUseCase(): FetchMoviesUseCase
    fun fetchMovieDetailsByIdUseCase(): FetchMovieDetailsByIdUseCase
    fun getMoviesUseCase(): GetMoviesUseCase
    fun searchMoviesUseCase(): SearchMoviesUseCase
    fun updateImageConfigsUseCase(): UpdateImageConfigsUseCase
    fun getImageConfigsUseCase(): GetImageConfigsUseCase
    fun getGenresUseCase(): GetGenresUseCase
    fun fetchGenresUseCase(): FetchGenresUseCase
}