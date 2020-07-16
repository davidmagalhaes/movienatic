package com.davidmag.movienatic.presentation.di

import com.davidmag.movienatic.presentation.viewmodel.MovieDetailsViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieListTabViewModel
import com.davidmag.movienatic.presentation.viewmodel.MovieTabHostViewModel
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import android.app.Application
import com.davidmag.movienatic.infrastructure.di.*
import com.davidmag.movienatic.presentation.activity.MovieDetailsActivity
import com.davidmag.movienatic.presentation.activity.MovieListTabFragment
import com.davidmag.movienatic.presentation.activity.MovieTabHostActivity
import dagger.BindsInstance

@PresentationScope
@Component(
    dependencies = [
        ApplicationComponent::class
    ],
    modules = [
        ViewModelModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface PresentationComponent {

    fun inject(movieTabHostActivity: MovieTabHostActivity)
    fun inject(movieListTabFragment: MovieListTabFragment)
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}