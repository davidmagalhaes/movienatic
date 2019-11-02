package com.davidmag.movienatic.presentation.di

import com.davidmag.movienatic.infrastructure.di.DatasourceModule
import com.davidmag.movienatic.infrastructure.di.NetworkModule
import com.davidmag.movienatic.infrastructure.di.RepositoryModule
import com.davidmag.movienatic.infrastructure.di.UseCaseModule
import com.davidmag.movienatic.presentation.viewmodel.MovieDetailsViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieListTabViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieTabHostViewModel
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        NetworkModule::class,
        DatasourceModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface PresentationComponent {
    fun inject(movieDetailsViewModel: MovieDetailsViewModel)
    fun inject(movieListTabViewModel: MovieListTabViewModel)
    fun inject(movieTabHostViewModel: MovieTabHostViewModel)
}