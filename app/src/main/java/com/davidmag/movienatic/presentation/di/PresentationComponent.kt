package com.davidmag.movienatic.presentation.di

import com.davidmag.movienatic.AppGlideModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import com.davidmag.movienatic.infrastructure.di.*
import com.davidmag.movienatic.presentation.fragmentactivity.MovieDetailsActivity
import com.davidmag.movienatic.presentation.fragmentactivity.MovieListFragment
import com.davidmag.movienatic.presentation.fragmentactivity.home.HomeActivity
import com.davidmag.movienatic.presentation.adapter.movie.MovieRecyclerViewAdapter
import com.davidmag.movienatic.presentation.common.DelegatedAdapter

@PresentationScope
@Component(
    dependencies = [
        ApplicationComponent::class
    ],
    modules = [
        ViewModelModule::class,
        AdapterDelegateModule::class,
        AdapterDelegatesManagerModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface PresentationComponent {
    fun inject(homeActivity: HomeActivity)
    fun inject(movieListFragment: MovieListFragment)
    fun inject(movieDetailsActivity: MovieDetailsActivity)

    fun inject(appGlideModule: AppGlideModule)

    fun inject(delegatedAdapter: DelegatedAdapter)
    fun inject(movieRecyclerViewAdapter: MovieRecyclerViewAdapter)
}