package com.davidmag.movienatic.presentation.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import com.davidmag.movienatic.infrastructure.di.*
import com.davidmag.movienatic.presentation.activity.MovieDetailsActivity
import com.davidmag.movienatic.presentation.activity.MovieListFragment
import com.davidmag.movienatic.presentation.activity.HomeActivity

@PresentationScope
@Component(
    dependencies = [
        ApplicationComponent::class
    ],
    modules = [
        ViewModelModule::class,
        ViewTypeDelegateModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface PresentationComponent {

    fun inject(homeActivity: HomeActivity)
    fun inject(movieListFragment: MovieListFragment)
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}